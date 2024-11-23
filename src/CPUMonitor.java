import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class CPUMonitor {
    private final OperatingSystemMXBean osBean;
    private static final double GiB = Math.pow(2, 30); // 1 GiB în bytes

    public CPUMonitor() {
        osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    public double getCpuLoad() {
        return osBean.getSystemCpuLoad() * 100; // CPU load în procente
    }

    public double getFreeMemory() {
        double freeRamSize = (double) osBean.getFreePhysicalMemorySize(); // RAM liber în bytes
        return freeRamSize / GiB; // Convertit în GiB
    }

    public double getTotalMemory() {
        double totalRamSize = (double) osBean.getTotalPhysicalMemorySize(); // RAM total în bytes
        return totalRamSize / GiB; // Convertit în GiB
    }
}
