package ua.jug.kotlindemo

import twitter4j.Status
import twitter4j.Twitter
import twitter4j.util.function.Consumer

class RetweetAction(val twitter: Twitter, val stopWords : List<String> = emptyList()) : Consumer<Status> {

    override fun accept(status: Status) {
        if(isAcceptable(status)) {
            twitter.retweetStatus(status.id)
        }
    }

    private fun isAcceptable(status: Status) =
            !(status.isRetweet or status.isRetweetedByMe) && isBadStatus(status)

    private fun isBadStatus(status: Status) = stopWords.none { it in status.text }
}