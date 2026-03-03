package space.parzival.discord.radiobot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PlatformCheck implements InitializingBean {
    private final List<String> SUPPORTED_LINUX_ARCHS = List.of("amd64", "x86_64", "aarch64");
    private final List<String> SUPPORTED_WINDOWS_ARCHS = List.of("amd64", "x86_64");
    private final List<String> SUPPORTED_DARWIN_ARCHS = List.of("x86_64");

    /**
     * Why is this check necessary? Good question.
     * </br>
     * Discord uses a specific protocol called the Discord Audio & Video End-toEnd Encryption Protocol (DAVE) for audio
     * transmission. This protocol relies on native libraries that are optimized for specific platforms.
     * You can read more about it <a href="https://daveprotocol.com/">on the Whitepaper page</a>.
     * </br>
     * Since we are forced to use said libraries, we need to ensure that the platform we are running on is supported by
     * them. This also means that we are limited to the platforms that are supported by the DAVE protocol.
     * </br>
     * A list of supported platforms can be found here:
     * * <a href="https://github.com/MinnDevelopment/jdave?tab=readme-ov-file#supported-platforms">JDAVE</a>
     * * <a href="https://github.com/MinnDevelopment/udpqueue.rs">udpqueue.rs</a>
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("Detected platform: OS: {}, Arch: {}", System.getProperty("os.name"), System.getProperty("os.arch"));
        boolean isSupported =
            (
                "linux".equalsIgnoreCase(System.getProperty("os.name")) &&
                SUPPORTED_LINUX_ARCHS.contains(System.getProperty("os.arch").toLowerCase())
            ) || (
                "windows".equalsIgnoreCase(System.getProperty("os.name")) &&
                SUPPORTED_WINDOWS_ARCHS.contains(System.getProperty("os.arch").toLowerCase())
            ) || (
                "darwin".equalsIgnoreCase(System.getProperty("os.name")) &&
                SUPPORTED_DARWIN_ARCHS.contains(System.getProperty("os.arch").toLowerCase())
            );

        if (!isSupported) {
            log.error("Your platform (OS: {}, Arch: {}) is not supported.", System.getProperty("os.name"), System.getProperty("os.arch"));
            System.exit(1);
        }
    }
}
