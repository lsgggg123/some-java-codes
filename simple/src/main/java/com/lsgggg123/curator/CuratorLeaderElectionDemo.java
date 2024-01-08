package com.lsgggg123.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * zk leader 选举，chat gpt 生成代码
 */
@Slf4j
public class CuratorLeaderElectionDemo {
    private static final String ZOOKEEPER_SERVER = "elastic-job-zk.sg.inc.antbank.com.cn:2181";
    private static final String LEADER_PATH      = "/leader-election";

    public static void main(String[] args) throws Exception {
        // 假设我们有3个实例来进行Leader选举
        for (int i = 1; i <= 3; i++) {
            final int instanceId = i;
            new Thread(() -> startLeaderElection(instanceId)).start();
        }
    }

    private static void startLeaderElection(int instanceId) {
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZOOKEEPER_SERVER, new ExponentialBackoffRetry(1000, 3));
        client.start();

        try (LeaderLatch latch = new LeaderLatch(client, LEADER_PATH, "Instance " + instanceId)) {
            latch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    log.info("Instance {} is now the leader.", instanceId);
                    // 可以在这里添加作为领导者的逻辑
                }

                @Override
                public void notLeader() {
                    log.info("Instance {} is not the leader anymore.", instanceId);
                }
            });

            latch.start();
            latch.await(); // 阻塞等待成为leader

            // 等待一段时间模拟 leader的工作过程
            Thread.sleep(5000L + RandomUtils.nextLong(5000, 10000));
            // Leader工作一段时间后，自动放弃领导权
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("catch exception", e);
        } finally {
            client.close();
        }
    }
}