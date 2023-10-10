package com.example.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.DefaultSecurityParameterNameDiscoverer;

import java.lang.reflect.Method;

public class CustomMethodSecurityEvaluationContext extends MethodBasedEvaluationContext {

    /**
     * Intended for testing. Don't use in practice as it creates a new parameter resolver
     * for each instance. Use the constructor which takes the resolver, as an argument
     * thus allowing for caching.
     */
    public CustomMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi) {
        this(user, mi, new DefaultSecurityParameterNameDiscoverer());
    }

    public CustomMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi,
                                                 ParameterNameDiscoverer parameterNameDiscoverer) {
        super(mi.getThis(), getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
    }

    public CustomMethodSecurityEvaluationContext(MethodSecurityExpressionOperations root, MethodInvocation mi,
                                                 ParameterNameDiscoverer parameterNameDiscoverer) {
        super(root, getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
    }

    private static Method getSpecificMethod(MethodInvocation mi) {
        return AopUtils.getMostSpecificMethod(mi.getMethod(), AopProxyUtils.ultimateTargetClass(mi.getThis()));
    }

}
