package ca.shawmedia.globalvideo;

import ca.shawmedia.globalvideo.gateways.IPlatformFeedsGateway;
import ca.shawmedia.globalvideo.gateways.stubs.PlatformFeedsGatewayStub;
import ca.shawmedia.globalvideo.infrastructure.IWebClient;
import ca.shawmedia.globalvideo.infrastructure.WebClient;
import ca.shawmedia.globalvideo.parsers.IRotatorContentParser;
import ca.shawmedia.globalvideo.parsers.RotatorContentParser;
import ca.shawmedia.globalvideo.services.ContentRotatorService;
import ca.shawmedia.globalvideo.services.IContentRotatorService;
import com.google.inject.Module;
import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

import java.util.List;

public class GlobalVideoApplication extends RoboApplication{

    @Override
    protected void addApplicationModules(List<Module> moduleList) {
        moduleList.add(new GlobalVideoModule());
    }

    static class GlobalVideoModule extends AbstractAndroidModule {

        @Override
        protected void configure() {
            bind(IWebClient.class).to(WebClient.class);
            bind(IRotatorContentParser.class).to(RotatorContentParser.class);
            bind(IPlatformFeedsGateway.class).to(PlatformFeedsGatewayStub.class);
            bind(IContentRotatorService.class).to(ContentRotatorService.class);
        }
    }
}
