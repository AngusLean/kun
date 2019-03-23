package com.doubleysoft.kun.mvc.server.netty;

import com.doubleysoft.kun.mvc.Server;
import com.doubleysoft.kun.mvc.server.netty.handler.KunHttpNettyHandler;
import com.doubleysoft.kun.mvc.server.netty.handler.MergeHttpRequestHandler;
import com.doubleysoft.kun.mvc.server.protocal.RequestProcess;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cupofish@gmail.com
 * 3/23/19 15:23
 */
@Slf4j
public class NettyServer implements Server {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private KunHttpNettyHandler kunHttpNettyHandler;

    @Override
    public void start(int port) {
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            kunHttpNettyHandler = new KunHttpNettyHandler();
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpServerExpectContinueHandler());
                            ch.pipeline().addLast(new MergeHttpRequestHandler());

                            ch.pipeline().addLast(new KunHttpNettyHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("error start server in port:{}", port, e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {
        try {
            if (this.bossGroup != null) {
                this.bossGroup.shutdownGracefully();
            }
            if (this.workerGroup != null) {
                this.workerGroup.shutdownGracefully();
            }
            log.info("shutdown successful");
        } catch (Exception e) {
            log.error("shutdown error", e);
        }
    }

    @Override
    public void stopNow() {
        this.stop();
    }

    @Override
    public void bindProcess(RequestProcess requestProcess) {

    }
}
