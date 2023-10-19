package com.liftoff.notificationservice.config.constants;

public final class ActivationConst {

    private ActivationConst() {
    }

    public static final String ACTIVATION_LINK_BASE =
            "http://frontend.page/";
    public static final String ACTIVATION_PATH =
            "activate";
    public static final String ACTIVATION_SUBJECT_TEMPLATE =
            "User Account Activation";
    public static final String ACTIVATION_BODY_HEADER_TEMPLATE =
            "<b>LOPI2 COFFEE SHOP</b><br><br>";
    public static final String ACTIVATION_BODY_TEMPLATE =
            "To confirm your registration, click on the link below:<br>";
    public static final String ACTIVATION_BODY_FOOTER_TEMPLATE =
            "<br><br>Best regards,<br>LOPI TEAM";

}