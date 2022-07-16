/*
 * Copyright (c) 2016 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digiblock.gui.remote;

import de.neemann.digiblock.gui.DigitalRemoteInterface;
import de.neemann.digiblock.lang.Lang;

import java.io.File;

/**
 * Handler to control the simulator.
 * The handler simply interprets the incoming request and calls the suited method
 * of the {@link DigitalRemoteInterface} which is implemented by the {@link de.neemann.digiblock.gui.Main} class.
 */
public class DigitalHandler implements HandlerInterface {
    private final DigitalRemoteInterface digitalRemoteInterface;

    /**
     * Creates a new server instance
     *
     * @param digitalRemoteInterface the remote interface which is used by the server
     */
    public DigitalHandler(DigitalRemoteInterface digitalRemoteInterface) {
        this.digitalRemoteInterface = digitalRemoteInterface;
    }

    @Override
    public String handleRequest(String request) {
        int p = request.indexOf(':');
        String command = request;
        String args = null;
        if (p >= 0) {
            command = request.substring(0, p);
            args = request.substring(p + 1);
        }

        try {
            String ret = handle(command.toLowerCase(), args);
            if (ret != null)
                return "ok:"+ret;
            else
                return "ok";
        } catch (RemoteException e) {
            return e.getMessage();
        }
    }

    private String handle(String command, String args) throws RemoteException {
        switch (command) {
            case "step":
                return digitalRemoteInterface.doSingleStep();
            case "start":
                digitalRemoteInterface.start(new File(args));
                return null;
            case "debug":
                digitalRemoteInterface.debug(new File(args));
                return null;
            case "run":
                return digitalRemoteInterface.runToBreak();
            case "stop":
                digitalRemoteInterface.stop();
                return null;
            default:
                throw new RemoteException(Lang.get("msg_remoteUnknownCommand", command));
        }
    }
}
