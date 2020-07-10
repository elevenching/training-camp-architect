package com.example.training.week5;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

/**
 * @author chenjun
 */
public class ConsistentHash {
    private final List<String> realNodes;

    public ConsistentHash(List<String> realNodes) {
        this.realNodes = realNodes;
    }

    public static Set<String> buildCacheKeys(int n) {
        Set<String> keySet = new HashSet<>();
        while (keySet.size() < n) {
            String key = UUID.randomUUID().toString();
            if (keySet.contains(key)) {
                continue;
            }
            keySet.add(key);
        }
        return keySet;
    }

    public static void main(String[] args) {
        ImmutableList<String> realNodes = ImmutableList.of(
                "10.9.4.10",
                "10.9.4.11",
                "10.9.4.12",
                "10.9.4.13",
                "10.9.4.14",
                "10.9.4.15",
                "10.9.4.16",
                "10.9.4.17",
                "10.9.4.18",
                "10.9.4.19");
        // 1.生成 keyCount 个 key
        int keyCount = 1_000_000;
        Set<String> cacheKeys = ConsistentHash.buildCacheKeys(keyCount);
        ConsistentHash consistentHash = new ConsistentHash(realNodes);
        double min = Double.MAX_VALUE;
        int suitableVirtualNodesPerRealNode = 0;
        int lowest = 220;
        int highest = 270;
        for (int i = lowest; i <= highest; i++) {
            // 2.按每个节点扩展到 i 个虚拟节点，构建虚拟节点(的 hash 值)对应真实节点的映射
            SortedMap<Integer, String> virtualHashToNodeMap = consistentHash.buildVirtualHashToNode(i);
            // 3.把 keyCount 个 key 分布到真实节点上去，得到真是节点分配的 key 数量
            Map<String, Integer> realNodeToKeyCountMap = consistentHash.distributeKeys(cacheKeys, virtualHashToNodeMap);
            Set<Double> keyCountPerRealNodeSet = realNodeToKeyCountMap.values().stream().map(Double::new).collect(Collectors.toSet());
            // 4.计算真实节点分配到的 key 数量的样本标准差
            double std = CalSta.sampleStdDev(keyCountPerRealNodeSet);
            // 5.寻找最小标准差及虚拟节点数
            if (min > std) {
                min = std;
                suitableVirtualNodesPerRealNode = i;
            }
            if (i % 5 == 0) {
                System.out.println(String.format("i: %s, std: %s", i, std));
            }
        }
        System.out.println(String.format("suitableVirtualNodesPerRealNode: %s, StdDeviation: %s", suitableVirtualNodesPerRealNode, min));
    }

    public SortedMap<Integer, String> buildVirtualHashToNode(int virtualNodePerRealNode) {
        SortedMap<Integer, String> virtualHashToNodeMap = new TreeMap<>();
        for (String node : realNodes) {
            for (int i = 0; i < virtualNodePerRealNode; i++) {
                String vNodeName = node + "&&VN" + i;
                int hash = getHash(vNodeName);
                virtualHashToNodeMap.put(hash, node);
            }
        }
        return virtualHashToNodeMap;
    }

    public Map<String, Integer> distributeKeys(Set<String> keys, SortedMap<Integer, String> virtualHashToNodeMap) {
        Map<String, Integer> realNodeToKeyCountMap = Maps.newHashMap();
        for (String s : keys) {
            int hash = getHash(s);
            SortedMap<Integer, String> subMap = virtualHashToNodeMap.tailMap(hash);
            String node = subMap.isEmpty() ? virtualHashToNodeMap.get(virtualHashToNodeMap.firstKey()) : subMap.get(subMap.firstKey());
            Integer keyCount = realNodeToKeyCountMap.getOrDefault(node, 0);
            realNodeToKeyCountMap.put(node, keyCount + 1);
        }
        return realNodeToKeyCountMap;
    }

    public int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

}
