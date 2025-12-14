package in.techpro424.itemblacklist.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import in.techpro424.itemblacklist.config.Config;
import net.minecraft.commands.CommandSourceStack;

import java.util.concurrent.CompletableFuture;

public class BlacklistSuggestionsProvider implements SuggestionProvider<CommandSourceStack> {
    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        Config.getBlacklist().forEach(builder::suggest);
        return builder.buildFuture();
    }
}
