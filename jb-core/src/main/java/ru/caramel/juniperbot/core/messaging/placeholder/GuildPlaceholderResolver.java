/*
 * This file is part of JuniperBotJ.
 *
 * JuniperBotJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * JuniperBotJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with JuniperBotJ. If not, see <http://www.gnu.org/licenses/>.
 */
package ru.caramel.juniperbot.core.messaging.placeholder;

import lombok.NonNull;
import net.dv8tion.jda.core.entities.Guild;
import org.springframework.context.ApplicationContext;
import ru.caramel.juniperbot.core.messaging.placeholder.node.FunctionalNodePlaceholderResolver;
import ru.caramel.juniperbot.core.messaging.placeholder.node.SingletonNodePlaceholderResolver;
import ru.caramel.juniperbot.core.service.MessageService;
import ru.caramel.juniperbot.core.utils.TriFunction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GuildPlaceholderResolver extends FunctionalNodePlaceholderResolver<Guild> {

    private final static Map<String, TriFunction<Guild, Locale, ApplicationContext, ?>> ACCESSORS;

    static {
        Map<String, TriFunction<Guild, Locale, ApplicationContext, ?>> accessors = new HashMap<>();
        accessors.put("id", (g, l, c) -> g.getId());
        accessors.put("name", (g, l, c) -> g.getName());
        accessors.put("region", (g, l, c) -> c.getBean(MessageService.class).getEnumTitle(g.getRegion()));
        accessors.put("afkTimeout", (g, l, c) ->  g.getAfkTimeout().getSeconds());
        accessors.put("afkChannel", (g, l, c) ->  g.getAfkChannel() != null ? g.getAfkChannel().getName() : "");
        accessors.put("memberCount", (g, l, c) ->  g.getMembers().size());
        accessors.put("createdAt", (g, l, c) -> DateTimePlaceholderResolver.of(g.getCreationTime(), l, g, c));
        ACCESSORS = Collections.unmodifiableMap(accessors);
    }

    private final Guild guild;

    public GuildPlaceholderResolver(@NonNull Guild guild,
                                    @NonNull Locale locale,
                                    @NonNull ApplicationContext applicationContext) {
        super(ACCESSORS, locale, applicationContext);
        this.guild = guild;
    }

    @Override
    protected Guild getObject() {
        return guild;
    }

    @Override
    public Object getValue() {
        return guild.getName();
    }

    public static SingletonNodePlaceholderResolver of(@NonNull Guild guild,
                                                      @NonNull Locale locale,
                                                      @NonNull ApplicationContext context,
                                                      @NonNull String name) {
        return new SingletonNodePlaceholderResolver(name, new GuildPlaceholderResolver(guild, locale, context));
    }
}
