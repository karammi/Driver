package ir.brn.driver.tweet

import ir.brn.driver.model.wall.Wall

interface TweetListener {
    fun likeTweet(wallId: Int)
    fun disLikeTweet(wallId: Int)
    fun editTweet(wall: Wall)
    fun deleteTweet(wallId: Int)
}