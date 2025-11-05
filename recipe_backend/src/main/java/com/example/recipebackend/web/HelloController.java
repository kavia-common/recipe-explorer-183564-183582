package com.example.recipebackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Tag(name = "System", description = "System and documentation endpoints")
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        return "Hello, Spring Boot! Welcome to recipebackend";
    }

    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI preserving original scheme/host/port")
    public RedirectView docs(HttpServletRequest request) {
        // Compute base URL honoring X-Forwarded headers for proxy deployments
        String scheme = headerOrDefault(request, "X-Forwarded-Proto", request.getScheme());
        String host = headerOrDefault(request, "X-Forwarded-Host", request.getServerName());
        String portHeader = request.getHeader("X-Forwarded-Port");
        int port = portHeader != null ? parsePort(portHeader, request.getServerPort()) : request.getServerPort();

        StringBuilder base = new StringBuilder();
        base.append(scheme).append("://").append(host);
        if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
            base.append(":").append(port);
        }
        String target = base + "/swagger-ui.html";

        RedirectView rv = new RedirectView(target);
        rv.setHttp10Compatible(false);
        return rv;
    }

    private String headerOrDefault(HttpServletRequest req, String name, String def) {
        String v = req.getHeader(name);
        return (v == null || v.isBlank()) ? def : v;
    }

    private int parsePort(String v, int def) {
        try { return Integer.parseInt(v); } catch (NumberFormatException e) { return def; }
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: recipebackend";
    }
}
