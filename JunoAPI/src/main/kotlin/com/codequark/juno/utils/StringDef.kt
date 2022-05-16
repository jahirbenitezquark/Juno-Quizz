package com.codequark.juno.utils

/**
 * Denotes that the annotated String element, represents a logical
 * type and that its value should be one of the explicitly named constants.
 *
 *
 * Example:
 * <pre>`
 * &#64;Retention(SOURCE)
 * &#64;StringDef({
 * POWER_SERVICE,
 * WINDOW_SERVICE,
 * LAYOUT_INFLATER_SERVICE
 * })
 * public @interface ServiceName {}
 * public static final String POWER_SERVICE = "power";
 * public static final String WINDOW_SERVICE = "window";
 * public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";
 * ...
 * public abstract Object getSystemService(@ServiceName String name);
`</pre> *
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class StringDef(
    /** Defines the allowed constants for this element  */
    vararg val value: String = [],
    /**
     * Whether any other values are allowed. Normally this is
     * not the case, but this allows you to specify a set of
     * expected constants, which helps code completion in the IDE
     * and documentation generation and so on, but without
     * flagging compilation warnings if other values are specified.
     */
    val open: Boolean = false
)