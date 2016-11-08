package br.com.m4u.migration.reload.enums;

/**
 * Created by sandro on 07/11/16.
 */
public enum ChannelEnum {

    TIM_WEB("WEB RECARGA TIM"),
    TIM_URA("URA RECARGA TIM"),
    TIM_ANDROID("TIM_ANDROID"),
    TIM_IPHONE("TIM_IPHONE");

    private String channel;

    ChannelEnum(String channel) {
        this.channel = channel;
    }

    public static String getChannel(String channel) {
        for (ChannelEnum c : ChannelEnum.values()) {
            if (c.getChannel().equals(channel)) {
                return c.name();
            }
        }
        return null;
    }

    public String getChannel() {
        return channel;
    }
}
