package tn.esprit.pi.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

@Service
public class GoogleCalService {

	private final static Log logger = LogFactory.getLog(GoogleCalService.class);
	private static final String APPLICATION_NAME = "";
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.calendar.Calendar client;

	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	Credential credential;

	@Value("${google.client.client-id}")
	private String clientId;
	@Value("${google.client.client-secret}")
	private String clientSecret;
	@Value("${google.client.redirectUri}")
	private String redirectURI;

	// private Set<Event> events = new HashSet<>();

	// To login
	@RequestMapping(value = "/login/google", method = RequestMethod.GET)
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
		return new RedirectView(authorize());
	}

	public String addEvent(String code) {
		com.google.api.services.calendar.model.Events eventList;
		String message;
		String calendarId = "primary";
		try {
			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
			credential = flow.createAndStoreCredential(response, "userID");
			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();
			// List of events
			Events events = client.events();

			// Convert date

			DateTime date = new DateTime("2021-03-01T09:00:00.000+01:00");

			eventList = events.list("primary").setTimeMin(date).setTimeMax(date).execute();
			System.out.println("Avannnnnt => " + eventList.getItems());
			if (!eventList.getItems().isEmpty()) {
				System.out.println("Doctor not avaibale!!");
			} else {
				// Create a consultation
				System.out.println("On va le créer!!!!");
				Event event = new Event().setSummary("Consultation").setLocation("Ariana");

				SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				
				EventDateTime start = new EventDateTime().setDateTime(date).setTimeZone("UTC");
				event.setStart(start);

				EventDateTime end = new EventDateTime().setDateTime(date).setTimeZone("UTC");
				event.setEnd(end);

				// String[] recurrence = new String[] {
				// "RRULE:FREQ=DAILY;COUNT=2" };
				// event.setRecurrence(Arrays.asList(recurrence));

				event = client.events().insert(calendarId, event).execute();
				System.out.printf("Event created: %s\n", event.getHtmlLink());

			}
			DateTime date1 = new DateTime("2021-02-25T09:00:00.000+01:00");
			DateTime date2 = new DateTime(new Date());

			eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
			message = eventList.getItems().toString();
			// System.out.println("My:" + eventList.getItems());
		} catch (Exception e) {
			logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
					+ " Redirecting to google connection status page.");
			message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
					+ " Redirecting to google connection status page.";
		}

		System.out.println("cal message:" + message);
		return message;
	}

	// Authorize
	public String authorize() throws Exception {
		AuthorizationCodeRequestUrl authorizationUrl;
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					Collections.singleton(CalendarScopes.CALENDAR)).build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
		System.out.println("cal authorizationUrl->" + authorizationUrl);
		System.out.println("redirect => " + redirectURI);
		return authorizationUrl.build();
	}

}
