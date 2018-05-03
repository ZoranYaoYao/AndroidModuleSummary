package com.zqs.baidu.tts.synthesis.bean;

/**
 * Created by zqs on 2018/5/2.
 */

public class Voice implements Comparable<Voice>{

    private String content;
    private int priority;
    private Type type;
    private boolean interrupt;  // false - 不能打断, true -可以打断

    public enum Type {
        TEXT,RING,FILE
    }

    public Voice(String content, int priority, Type type) {
        this(content,priority,type,true);
    }

    public Voice(String content, int priority, Type type, boolean interrupt) {
        this.content = content;
        this.priority = priority;
        this.type = type;
        this.interrupt = interrupt;
    }

    /**
     * PriorityQueue 默认排序是从小到大
     */
    @Override
    public int compareTo(Voice o) {
        if (o.priority - priority == 0) {
            return 1;
        }
        return o.priority - priority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public boolean canInterrupt() {
        return interrupt;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }
}
