package org.diosoft.spring.mvcTask.configuration;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan("org.diosoft.spring.mvcTask")
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		configurer.setTaskExecutor(new AsyncTaskExecutor() {

			@Override
			public void execute(Runnable task, long startTimeout) {
				try {
					executorService.awaitTermination(startTimeout, TimeUnit.MILLISECONDS);
					executorService.execute(task);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
			@Override
			public Future<?> submit(Runnable task) {
				return executorService.submit(task);
			}
			@Override
			public <T> Future<T> submit(Callable<T> task) {
				return executorService.submit(task);
			}
			@Override
			public void execute(final Runnable task) {
				executorService.execute(task);
			}
		});
		super.configureAsyncSupport(configurer);
	}

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }
}
