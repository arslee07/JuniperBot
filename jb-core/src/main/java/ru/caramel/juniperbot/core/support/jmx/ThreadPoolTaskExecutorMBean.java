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
package ru.caramel.juniperbot.core.support.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.Objects;

@ManagedResource
public class ThreadPoolTaskExecutorMBean implements JmxNamedResource {

    private final org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor taskExecutor;

    public ThreadPoolTaskExecutorMBean(org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor taskExecutor) {
        Objects.requireNonNull(taskExecutor);
        this.taskExecutor = taskExecutor;
    }

    @ManagedAttribute(description = "Returns the number of threads that execute tasks")
    public int getActiveCount() {
        return taskExecutor.getActiveCount();
    }

    @ManagedAttribute(description = "Return the current pool size")
    public int getPoolSize() {
        return taskExecutor.getPoolSize();
    }

    @ManagedAttribute(description = "Returns the size of the core pool of threads")
    public int getCorePoolSize() {
        return taskExecutor.getCorePoolSize();
    }

    @ManagedAttribute(description = "Sets the core size of the pool")
    public void setCorePoolSize(int corePoolSize) {
        taskExecutor.setCorePoolSize(corePoolSize);
    }

    @ManagedAttribute(description = "Returns the max size allowed in the pool of threads")
    public int getMaxPoolSize() {
        return taskExecutor.getMaxPoolSize();
    }

    @ManagedAttribute(description = "Sets the max size allowed in the pool of threads")
    public void setMaxPoolSize(int maxPoolSize) {
        taskExecutor.setMaxPoolSize(maxPoolSize);
    }

    @ManagedAttribute(description = "Returns the number of keep-alive seconds")
    public int getKeepAliveSeconds() {
        return taskExecutor.getKeepAliveSeconds();
    }

    @ManagedAttribute(description = "Sets the keep-alive seconds sizein the pool of threads")
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
    }

    @ManagedAttribute(description = "Sets the queue capacity the pool of threads")
    public void setQueueCapacity(int queueCapacity) {
        taskExecutor.setQueueCapacity(queueCapacity);
    }

    @ManagedAttribute(description = "Allow core thread time-out")
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        taskExecutor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
    }

    @ManagedAttribute(description = "Returns the total number of completed tasks")
    public long getCompletedTaskCount() {
        return taskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
    }

    @ManagedAttribute(description = "Returns the largest number of threads that have been in the pool")
    public int getLargestPoolSize() {
        return taskExecutor.getThreadPoolExecutor().getLargestPoolSize();
    }

    @ManagedAttribute(description = "Returns the size of current queue")
    public int getQueueSize() {
        return taskExecutor.getThreadPoolExecutor().getQueue().size();
    }

    @ManagedAttribute(description = "Returns the number of additional elements that this queue can "
            + "accept without  "
            + "blocking")
    public int getQueueRemainingCapacity() {
        return taskExecutor.getThreadPoolExecutor().getQueue().remainingCapacity();
    }

    @ManagedAttribute(description = "Returns the total number of tasks that have ever been scheduled for execution ")
    public long getTaskCount() {
        return taskExecutor.getThreadPoolExecutor().getTaskCount();
    }

    @Override
    public String getJmxName() {
        return "Spring TaskExecutor Pool";
    }

    @Override
    public String[] getJmxPath() {
        return new String[] { };
    }
}
