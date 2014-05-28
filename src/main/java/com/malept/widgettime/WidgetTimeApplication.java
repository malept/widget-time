package com.malept.widgettime;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.malept.widgettime.resources.TimeResource;

public class WidgetTimeApplication extends Application<WidgetTimeConfiguration> {
    public static void main(String[] args) throws Exception {
        new WidgetTimeApplication().run(args);
    }

    @Override
    public void run(WidgetTimeConfiguration configuration,
                    Environment environment) {
        final TimeResource time_resource = new TimeResource();
        environment.jersey().register(time_resource);
    }

}
