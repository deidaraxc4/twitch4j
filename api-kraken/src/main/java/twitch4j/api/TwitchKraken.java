package twitch4j.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.web.client.RestTemplate;
import twitch4j.api.kraken.endpoints.*;

@Getter
@Deprecated
@Accessors(fluent = true)
public class TwitchKraken {

	private final ChatEndpoint chatEndpoint;
	private final UnofficialEndpoint unofficialEndpoint;
	private final TMIEndpoint tmiEndpoint;
	private final VideoEndpoint videoEndpoint;
	private final TeamEndpoint teamEndpoint;
	private final SearchEndpoint searchEndpoint;
	private final IngestEndpoint ingestEndpoint;
	private final CommunityEndpoint communityEndpoint;
	private final UserEndpoint userEndpoint;
	private final StreamEndpoint streamEndpoint;
	private final GameEndpoint gameEndpoint;
	private final ChannelEndpoint channelEndpoint;
	private final KrakenEndpoint krakenEndpoint;

	public TwitchKraken(RestTemplate restTemplate) {
		this.chatEndpoint = new ChatEndpoint(restTemplate);
		this.unofficialEndpoint = new UnofficialEndpoint(restTemplate);
		this.tmiEndpoint = new TMIEndpoint(restTemplate);
		this.videoEndpoint = new VideoEndpoint(restTemplate);
		this.teamEndpoint = new TeamEndpoint(restTemplate);
		this.searchEndpoint = new SearchEndpoint(restTemplate);
		this.ingestEndpoint = new IngestEndpoint(restTemplate);
		this.communityEndpoint = new CommunityEndpoint(restTemplate);
		this.userEndpoint = new UserEndpoint(restTemplate);
		this.streamEndpoint = new StreamEndpoint(restTemplate);
		this.gameEndpoint = new GameEndpoint(restTemplate);
		this.channelEndpoint = new ChannelEndpoint(restTemplate);
		this.krakenEndpoint = new KrakenEndpoint(restTemplate);
	}
}
