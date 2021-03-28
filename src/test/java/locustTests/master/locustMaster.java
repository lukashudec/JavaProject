package locustTests.master;

import java.io.IOException;

public class locustMaster {
    ProcessBuilder masterProcess;
    String[] arguments;
    public String path;
    public String host;
    public int port;

    public locustMaster(String path, String host, String port) {
        this.path=path;
        this.host=host;
        this.port=Integer.parseInt(port);
        arguments= new String[]{"python", "-m", "locust", "-f",
                path,
                "--master", "--master-bind-host=" + host, "--master-bind-port=" + port};
        masterProcess = new ProcessBuilder(arguments);
    }

    public locustMaster(String path) {
        this(path,"127.0.0.1","5557");
    }

    public ProcessBuilder getProcess() {
        return masterProcess;
    }

    public locustMaster redirectIO() {
        masterProcess.redirectErrorStream(true);
        masterProcess.inheritIO();
        return this;
    }

    public locustMaster start() throws IOException {
        masterProcess.start();
        return this;
    }
}
