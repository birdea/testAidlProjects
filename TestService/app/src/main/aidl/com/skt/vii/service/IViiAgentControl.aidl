package com.skt.vii.service;

interface IViiAgentControl {

    /**
     *  Agent의 Wakeup Detector를 활성한다.
     */
    oneway void startWakeupDetector();

    /**
     *  Agent의 Wakeup Detector를 비활성한다.
     */
    oneway void stopWakeupDetector();

    /**
     *  Agent의 내부 동작을 중지한다. (Media, TTS, Alert, etc)
     */
    oneway void stopActivity();

    /**
     * 양방향 IPC 통신용으로 Agent의 Stub 객체를 서버측에 등록한다.
     */
    oneway void registerAgentControl(IViiAgentControl agent);

    /**
     * 양방향 IPC 통신용으로 Agent의 Stub 객체를 서버측에 해제한다.
     */
    oneway void unregisterAgentControl(IViiAgentControl agent);

    /**
     *  Agent의 내부 활동 상태값을 수신한다.
     */
    oneway void onAgentStatus(int status, String service);

    /**
     *  Agent를 활성한다. (NUGU_CALL 특화 기능 / 일반 케이스에서 사용하지 않음)
     */
    oneway void forceStartAgent();

    /**
     *  Agent를 비활성한다. (NUGU_CALL 특화 기능 / 일반 케이스에서 사용하지 않음)
     */
    oneway void forceStopAgent();
}