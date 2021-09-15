package com.skt.vii.service;

interface IViiAgentControl {

   /**
     *  Agent를 활성한다.
     */
    oneway void activateAgent();

    /**
     *  Agent를 비활성한다.
     */
    oneway void deactivateAgent();

    /**
     *  Agent의 Wake Word Detector를 활성한다.
     */
    oneway void activateWakeWordDetector();

    /**
     *  Agent의 Wake Word Detector를 비활성한다.
     */
    oneway void deactivateWakeWordDetector();

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
}