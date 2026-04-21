package vn.hblab.training.tracker.application.security;

public interface TokenGenerator {
    String generate(String userName);
}
