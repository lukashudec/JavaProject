package locustTests.master;

import utilityClasses.utility.resourceMapping;

import java.io.IOException;

public class LocustMaster {
    ProcessBuilder masterProcess;
    public String path;
    public String host;
    public int port;

    public LocustMaster(String path, String host, String port) {
        this.path=path;
        this.host=host;
        this.port=Integer.parseInt(port);

        masterProcess = new ProcessBuilder("python", "-m", "locust", "-f", path,
                "--master", "--master-bind-host=" + host, "--master-bind-port=" + port);
    }

    public LocustMaster(String path) {
        this(path,"127.0.0.1","5557");
    }

    public LocustMaster() {
        this(resourceMapping.pathToMasterPy);
    }

    public ProcessBuilder getProcess() {
        return masterProcess;
    }

    public LocustMaster redirectIO() {
        masterProcess.redirectErrorStream(true);
        masterProcess.inheritIO();
        return this;
    }

    public LocustMaster start() throws IOException {
        masterProcess.start();
        return this;
    }
}
