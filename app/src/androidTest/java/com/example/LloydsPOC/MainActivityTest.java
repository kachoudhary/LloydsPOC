package com.example.LloydsPOC;

import android.content.Context;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.InstrumentationRegistry;
import com.example.lloydsPOC.R;
import com.example.lloydsPOC.view.MainActivity;
import com.example.lloydsPOC.utils.Constants;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    static final int TOTAL_ITEMS_COUNT = 10;
    static final String TOP_ARTISTS_JSON_FILE = "top_artists.json";
    static final String TOP_ALBUMS_JSON_FILE = "top_albums.json";
    static final String TOP_ARTISTS_REQUEST_URL = "/test/?method=user.gettopartists&format=json&user=drrobbins&limit=5&api_key=35066a49f2deb23a3c35fd48ff5c9869";
    static final String TOP_ALBUMS_REQUEST_URL = "/test/?method=user.gettopalbums&format=json&user=drrobbins&limit=5&api_key=35066a49f2deb23a3c35fd48ff5c9869";
    static final String LAST_EXPECTED_ARTIST_NAME = "X-Dream";
    static final String LAST_EXPECTED_ARTIST_PLAY_COUNT = "30";
    static final String LAST_EXPECTED_ALBUM_NAME = "Iowa";
    static final String LAST_EXPECTED_ALBUM__PLAY_COUNT = "1163";
    static final String LAST_EXPECTED_ALBUM_ARTIST = "Slipknot";
    private static MockWebServer mockWebServer;


    @Rule
    public ActivityTestRule<MainActivity> categoryDetailActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @BeforeClass
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        Constants.BASE_URL = mockWebServer.url("test/").toString();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
                if (recordedRequest.getPath().equalsIgnoreCase(TOP_ARTISTS_REQUEST_URL)) {
                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(AssetsFileReader.readFileAsString(TOP_ARTISTS_JSON_FILE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (recordedRequest.getPath().equalsIgnoreCase(TOP_ALBUMS_REQUEST_URL)) {

                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(AssetsFileReader.readFileAsString(TOP_ALBUMS_JSON_FILE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        });
    }

    @Test
    public void testDataDisplay() {
        // cehck number of displayed artists
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT));
        // scroll to the last item
        onView(withId(R.id.rclr_artists)).perform(scrollToPosition(TOTAL_ITEMS_COUNT - 1));
        // check last item data is displayed correctly
        checkArtistItem(LAST_EXPECTED_ARTIST_NAME, LAST_EXPECTED_ARTIST_PLAY_COUNT, TOTAL_ITEMS_COUNT - 1);
        // go to top albums
        onView(withId(R.id.vp_main)).perform(new ViewPagerSwipeToPositionAction(1));
        // check number of displayed albums
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemsCountAssertion(TOTAL_ITEMS_COUNT));
        //scroll to the last album
        onView(withId(R.id.rclr_albums)).perform(scrollToPosition(TOTAL_ITEMS_COUNT - 1));
        // check last album data displayed correctly
        checkAlbumItem(LAST_EXPECTED_ALBUM_NAME, LAST_EXPECTED_ALBUM__PLAY_COUNT, LAST_EXPECTED_ALBUM_ARTIST, TOTAL_ITEMS_COUNT - 1);
        // go to top albums
        onView(withId(R.id.vp_main)).perform(new ViewPagerSwipeToPositionAction(2));
    }


    private void checkArtistItem(String expectedArtistName, String expectedArtistPlayCount, int itemPosition) {
// match artist name
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_artist_name,
                expectedArtistName,
                itemPosition));
        // match artist playcount
        onView(withId(R.id.rclr_artists)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedArtistPlayCount,
                itemPosition));
    }


    private void checkAlbumItem(String expectedAlbumName, String expectedAlbumPlayCount, String expectedAlbumArtist, int itemPosition) {
        // check album name
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_album_name,
                expectedAlbumName,
                itemPosition));
        // check album artist
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_album_artist,
                expectedAlbumArtist,
                itemPosition));

        // match album playcount
        onView(withId(R.id.rclr_albums)).check(new RecyclerViewItemStringDataAssertion(
                R.id.txt_plays,
                expectedAlbumPlayCount,
                itemPosition));
    }
}