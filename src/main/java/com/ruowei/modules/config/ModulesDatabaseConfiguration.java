package com.ruowei.modules.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * repositories 扫描配置
 * @author 刘东奇
 * @date 2019/9/8
 */
@Configuration
@EnableJpaRepositories("com.ruowei.modules.*.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class ModulesDatabaseConfiguration {
}
