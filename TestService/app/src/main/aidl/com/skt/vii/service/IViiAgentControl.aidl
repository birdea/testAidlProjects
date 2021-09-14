package com.skt.vii.service;

interface IViiAgentControl {

    /**
     *  Agent 서비스를 시작한다.
     */
    oneway void startAgent();

    /**
     *  Agent 서비스를 중지한다. (특정 동작 실행 중 Agent가 동작하지 않아아 한다는 UX 정책 관련)
     */
    oneway void stopAgent();

    /**
     *  Agent 서비스 내부 활동(Media, TTS, Alert, etc)을 중지한다.
     */
    oneway void stopActivity();

    /**
     * Agent 서비스 내부 활동 상태값을 수신한다.
     */
    oneway void onAgentStatus(int status, String service);

    /**
     * Agent의 Wake Word Detector를 동작 여부를 제어한다.
     */
    oneway void setWakeWordDetector(boolean enable);

    /**
     * 양방향 IPC 통신용으로 Agent의 Stub 객체를 서버측에 등록한다.
     */
    oneway void registerAgentControl(IViiAgentControl agent);

    /**
     * 양방향 IPC 통신용으로 Agent의 Stub 객체를 서버측에 해제한다.
     */
    oneway void unregisterAgentControl(IViiAgentControl agent);
}