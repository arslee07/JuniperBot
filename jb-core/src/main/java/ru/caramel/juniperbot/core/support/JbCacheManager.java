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
package ru.caramel.juniperbot.core.support;

import org.springframework.cache.CacheManager;
import ru.caramel.juniperbot.core.common.persistence.base.BaseEntity;

import java.util.function.Function;

public interface JbCacheManager extends CacheManager {

    <T extends BaseEntity> T get(Class<T> clazz, Long id, Function<Long, T> supplier);

    <T extends BaseEntity>  void evict(Class<T> clazz, Long id);

    <T, K> T get(String cacheName, K key, Function<K, T> supplier);

    <K> void evict(String cacheName, K key);
}
