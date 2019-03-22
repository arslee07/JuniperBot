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
package ru.caramel.juniperbot.web.dto.config;

import lombok.Getter;
import lombok.Setter;
import ru.caramel.juniperbot.core.moderation.model.WarnExceedAction;
import ru.caramel.juniperbot.core.moderation.persistence.ModerationConfig;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ModerationConfigDto implements Serializable {

    private static final long serialVersionUID = 2373520739258476656L;

    private Set<String> roles;

    private boolean publicColors;

    @Min(2)
    private int maxWarnings = ModerationConfig.DEFAULT_MAX_WARNINGS;

    @NotNull
    private WarnExceedAction warnExceedAction = WarnExceedAction.BAN;

    @Min(1)
    private int muteCount = ModerationConfig.DEFAULT_MUTE_COUNT;

    private boolean coolDownIgnored;
}
