package net.turniptales.buildingserver.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@Log4j2
public class Api {

    public CitizenPermission getPermission(UUID uuid) {
        Gson gson = new GsonBuilder().create();
        String url = "/citizen/" + uuid.toString() + "/permission";
        return gson.fromJson(sendGetRequest(url), CitizenPermission.class);
    }

    private String sendGetRequest(String url) {
        return getClient(url).method(GET)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private WebClient getClient(String url) {
        return WebClient.builder()
                .baseUrl("https://turniptales.net:7200/turniptalesapi/v1" + url)
                .defaultHeader("Authorization", "Basic dHVybmlwdGFsZXMtYXBpdXNlcjoqYnp0OThjV3EvLCckOT41SEN7NlEtTycralZFZmprMQ==")
                .codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(-1))
                .build();
    }

    public record CitizenPermission(List<String> teams, String role, String criminalOrganisation, String faction,
                                    String factionDisplayName) {

        public boolean isBuilder() {
            return isBuilderLeader() || this.teams.contains("BUILDER") || this.role.equals("MODERATOR") || this.role.equals("DEVELOPER");
        }

        public boolean isBuilderLeader() {
            return this.role.equals("ADMINISTRATOR") || role.equals("HEAD_OF_DEVELOPMENT") || role.equals("SENIOR_MODERATOR") || role.equals("TEAM_HEAD_BUILDER");
        }
    }
}
