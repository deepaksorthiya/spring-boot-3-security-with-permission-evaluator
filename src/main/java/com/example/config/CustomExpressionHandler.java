package com.example.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    @Override
    public EvaluationContext createEvaluationContext(Supplier<Authentication> authentication, MethodInvocation mi) {
        CustomSecurityExpressionRoot root = createSecurityExpressionRoot(authentication, mi);
        CustomMethodSecurityEvaluationContext ctx = new CustomMethodSecurityEvaluationContext(root, mi,
                getParameterNameDiscoverer());
        ctx.setBeanResolver(getBeanResolver());
        return ctx;
    }

    private CustomSecurityExpressionRoot createSecurityExpressionRoot(Supplier<Authentication> authentication,
                                                                      MethodInvocation invocation) {
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(new CustomPermissionEvaluator());
        root.setTrustResolver(getTrustResolver());
        root.setRoleHierarchy(getRoleHierarchy());
        root.setDefaultRolePrefix(getDefaultRolePrefix());
        return root;
    }
}