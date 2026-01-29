package com.broadcastc.notifications.provider;

import com.broadcastc.notifications.model.ChannelType;

public class ProviderBuilder {
	
	private String name;
    private NotificationProvider notificationProvider;
    private ChannelType channelType;
    
    private ProviderBuilder() {}
    
    public static ProviderBuilder builder() {
        return new ProviderBuilder();
    }
    
    public ProviderBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public ProviderBuilder notificationProvider(NotificationProvider provider) {
        this.notificationProvider = provider;
        return this;
    }
    
    
    public ProviderBuilder channelType(ChannelType channelType) {
        this.channelType = channelType;
        return this;
    }
    
    public Provider build() {
        if (name == null) {
            throw new IllegalStateException("name es obligatorio");
        }

        return new DefaultProvider(
                name,
                channelType,
                notificationProvider
        );
    }

}
