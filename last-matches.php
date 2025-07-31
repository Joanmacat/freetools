/*
 * Fetch last matches function for the home.
 */

function fetch_last_matches() {
    $api_url = 'https://www.thesportsdb.com/api/v1/json/3/eventslast.php?id=133739';

    // Fetch data from the API
    $response = wp_remote_get($api_url);

    // Check for errors
    if (is_wp_error($response)) {
        return '<p>Unable to retrieve match data at the moment.</p>';
    }

    $body = wp_remote_retrieve_body($response);
    $data = json_decode($body);

    if (empty($data->results)) {
        return '<p>No match data available.</p>';
    }

    // Limit to the last 3 matches
    $matches = array_slice($data->results, 0, 3);

    // Build HTML output
    $output = '<div class="matches-container">';
    foreach ($matches as $match) {
        $output .= '<div class="match">';
        $output .= '<div class="match-event"><strong>' . esc_html($match->strEvent) . '</strong><br></div>';
        $output .= '<div class="match-date">' . esc_html($match->dateEventLocal) . '<br></div>';
        $output .= '<div class="match-score">' . esc_html($match->intHomeScore) . ' - ' . esc_html($match->intAwayScore) . '</div>';
        $output .= '</div><hr>';
    }
    $output .= '</div>';

    return $output;
}
add_shortcode('last_matches', 'fetch_last_matches');
