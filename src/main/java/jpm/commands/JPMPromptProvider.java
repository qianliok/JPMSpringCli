package jpm.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author Qian Li
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JPMPromptProvider extends DefaultPromptProvider {
	@Override
	public String getPrompt() {
		return "jpm-shell>";
	}

	
	@Override
	public String getProviderName() {
		return "JPM prompt provider";
	}

}
