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
package ru.caramel.juniperbot.core.feature.provider;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import ru.caramel.juniperbot.core.common.service.DiscordService;
import ru.caramel.juniperbot.core.feature.model.FeatureProvider;
import ru.caramel.juniperbot.core.feature.model.FeatureSet;
import ru.caramel.juniperbot.core.support.model.SupportConfiguration;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@FeatureProvider(priority = 2)
public class SupportRolesFeatureSetProvider extends BaseOwnerFeatureSetProvider {

    @Autowired
    private SupportConfiguration configuration;

    @Autowired
    private DiscordService discordService;

    @Override
    public Set<FeatureSet> getByUser(long userId) {
        if (!discordService.isConnected(configuration.getGuildId())) {
            return Collections.emptySet();
        }
        Guild guild = discordService.getGuildById(configuration.getGuildId());
        if (guild == null) {
            return Collections.emptySet();
        }
        Member member = guild.getMemberById(userId);
        if (member == null) {
            return Collections.emptySet();
        }
        return member.getRoles().stream()
                .map(r -> configuration.getFeaturedRoles().get(r.getId()))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
