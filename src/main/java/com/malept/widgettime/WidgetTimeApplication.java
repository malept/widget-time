package com.malept.widgettime;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.malept.widgettime.resources.TimeResource;
import com.malept.widgettime.resources.WidgetResource;

public class WidgetTimeApplication extends Application<WidgetTimeConfiguration> {
    public static void main(String[] args) throws Exception {
        new WidgetTimeApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<WidgetTimeConfiguration> bootstrap) {
        // Assets in Dropwizard 0.7: https://groups.google.com/forum/#!topic/dropwizard-user/AsbhMeTOftc
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(WidgetTimeConfiguration configuration,
                    Environment environment) {
        final TimeResource time_resource = new TimeResource();
        final WidgetResource widget_resource = new WidgetResource();
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(time_resource);
        environment.jersey().register(widget_resource);
    }

}
