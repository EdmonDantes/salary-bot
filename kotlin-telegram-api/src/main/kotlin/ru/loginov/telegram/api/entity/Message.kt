/*
 * Copyright (c) 2022. Ilia Loginov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.loginov.telegram.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * This object represents a message.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Message(
        /**
         * Unique message identifier inside this chat
         */
        @JsonProperty(value = "message_id", required = true) val id: Long,
        /**
         * Sender of the message; empty for messages sent to channels.
         * For backward compatibility, the field contains a fake sender user
         * in non-channel chats, if the message was sent on behalf of a chat.
         *
         * *Optional*
         */
        @JsonProperty(value = "from", required = false) val from: User? = null,
        /**
         * Sender of the message, sent on behalf of a chat.
         * For example, the channel itself for channel posts,
         * the supergroup itself for messages from anonymous group administrators,
         * the linked channel for messages automatically forwarded to the discussion group.
         * For backward compatibility, the field from contains a fake sender user
         * in non-channel chats, if the message was sent on behalf of a chat.
         *
         * *Optional*
         */
        @JsonProperty(value = "sender_chat", required = false) val senderChat: Chat? = null,
        /**
         * Date the message was sent in Unix time
         */
        @JsonProperty(value = "date", required = true) val sendDateTime: Long,
        /**
         * Conversation the message belongs to
         */
        @JsonProperty(value = "chat", required = true) val chat: Chat,
        /**
         * For forwarded messages, sender of the original message
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_from", required = false) val forwardFrom: User? = null,
        /**
         * For messages forwarded from channels or from anonymous administrators,
         * information about the original sender chat
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_from_chat", required = false) val forwardFromChat: Chat? = null,
        /**
         * For messages forwarded from channels, identifier of the original message in the channel
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_from_message_id", required = false) val forwardFromMessageId: Long? = null,
        /**
         * For forwarded messages that were originally sent in channels
         * or by an anonymous chat administrator, signature of the message sender if present
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_signature", required = false) val forwardSignature: String? = null,
        /**
         * Sender's name for messages forwarded from users who disallow adding
         * a link to their account in forwarded messages
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_sender_name", required = false) val forwardSenderName: String? = null,
        /**
         * For forwarded messages, date the original message was sent in Unix time
         *
         * *Optional*
         */
        @JsonProperty(value = "forward_date", required = false) val forwardDateTime: Long? = null,
        /**
         * True, if the message is a channel post that was automatically
         * forwarded to the connected discussion group
         *
         * *Optional*
         */
        @JsonProperty(value = "is_automatic_forward", required = false) val isAutomaticForward: Boolean? = null,
        /**
         * For replies, the original message.
         * Note that the Message object in this field will not contain
         * further [replyToMessage] fields even if it itself is a reply.
         *
         * *Optional*
         */
        @JsonProperty(value = "reply_to_message", required = false) val replyToMessage: Message? = null,
        /**
         * Bot through which the message was sent
         *
         * *Optional*
         */
        @JsonProperty(value = "via_bot", required = false) val viaBot: User? = null,
        /**
         * Date the message was last edited in Unix time
         *
         * *Optional*
         */
        @JsonProperty(value = "edit_date", required = false) val editDateTime: Long? = null,
        /**
         * True, if the message can't be forwarded
         *
         * *Optional*
         */
        @JsonProperty(value = "has_protected_content", required = false) val hasProtectedContent: Boolean? = null,
        /**
         * The unique identifier of a media message group this message belongs to
         *
         * *Optional*
         */
        @JsonProperty(value = "media_group_id", required = false) val mediaGroupId: String? = null,
        /**
         * Signature of the post author for messages in channels,
         * or the custom title of an anonymous group administrator
         *
         * *Optional*
         */
        @JsonProperty(value = "author_signature", required = false) val authorSignature: String? = null,
        /**
         * For text messages, the actual UTF-8 text of the message, 0-4096 characters
         *
         * *Optional*
         */
        @JsonProperty(value = "text", required = true) val text: String,
        /**
         * For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
         *
         * *Optional*
         */
        @JsonProperty(value = "entities", required = false) val entities: List<MessageEntity>? = null,
        /**
         * Message is an animation, information about the animation.
         * For backward compatibility, when this field is set, the document field will also be set
         *
         * *Optional*
         */
        @JsonProperty(value = "animation", required = false) val animation: Animation? = null,
        /**
         * Message is an audio file, information about the file
         *
         * *Optional*
         */
        @JsonProperty(value = "audio", required = false) val audio: Audio? = null,
        /**
         * Message is a general file, information about the file
         *
         * *Optional*
         */
        @JsonProperty(value = "document", required = false) val document: Document? = null,
        /**
         * Message is a photo, available sizes of the photo
         *
         * *Optional*
         */
        @JsonProperty(value = "photo", required = false) val photo: List<PhotoSize>? = null,
        /**
         * Message is a sticker, information about the sticker
         *
         * *Optional*
         */
        @JsonProperty(value = "sticker", required = false) val sticker: Sticker? = null,
        /**
         * Message is a video, information about the video
         *
         * *Optional*
         */
        @JsonProperty(value = "video", required = false) val video: Video? = null,
        /**
         * Message is a video note, information about the video message
         *
         * *Optional*
         */
        @JsonProperty(value = "video_note", required = false) val videoNote: VideoNote? = null,
        /**
         * Message is a voice message, information about the file
         *
         * *Optional*
         */
        @JsonProperty(value = "voice", required = false) val voice: Voice? = null,
        /**
         * Caption for the animation, audio, document, photo, video or voice, 0-1024 characters
         *
         * *Optional*
         */
        @JsonProperty(value = "caption", required = false) val caption: String? = null,
        /**
         * For messages with a caption, special entities like usernames, URLs,
         * bot commands, etc. that appear in the caption
         *
         * *Optional*
         */
        @JsonProperty(value = "caption_entities", required = false) val captionEntities: List<MessageEntity>? = null,
        /**
         * Message is a shared contact, information about the contact
         *
         * *Optional*
         */
        @JsonProperty(value = "contact", required = false) val contact: Contact? = null,
        /**
         * Message is a dice with random value
         *
         * *Optional*
         */
        @JsonProperty(value = "dice", required = false) val dice: Dice? = null,
        /**
         * Message is a game, information about the game.
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "game", required = false) val game: Game?,
        /**
         * Message is a native poll, information about the poll
         *
         * *Optional*
         */
        @JsonProperty(value = "poll", required = false) val poll: Poll? = null,
        /**
         * Message is a venue, information about the venue.
         * For backward compatibility, when this field is set, the location field will also be set
         *
         * *Optional*
         */
        @JsonProperty(value = "venue", required = false) val venue: Venue? = null,
        /**
         * Message is a shared location, information about the location
         *
         * *Optional*
         */
        @JsonProperty(value = "location", required = false) val location: Location? = null,
        /**
         * New members that were added to the group or supergroup
         * and information about them (the bot itself may be one of these members)
         *
         * *Optional*
         */
        @JsonProperty(value = "new_chat_members", required = false) val newChatMembers: List<User>? = null,
        /**
         * A member was removed from the group, information about them (this member may be the bot itself)
         *
         * *Optional*
         */
        @JsonProperty(value = "left_chat_member", required = false) val leftChatMembers: User? = null,
        /**
         * A chat title was changed to this value
         *
         * *Optional*
         */
        @JsonProperty(value = "new_char_title", required = false) val newChatTitle: String? = null,
        /**
         * A chat photo was change to this value
         *
         * *Optional*
         */
        @JsonProperty(value = "new_char_photo", required = false) val newChatPhoto: List<PhotoSize>? = null,
        /**
         * Service message: the chat photo was deleted
         *
         * *Optional*
         */
        @JsonProperty(value = "delete_chat_photo", required = false) val deleteChatPhoto: Boolean? = null,
        /**
         * Service message: the group has been created
         *
         * *Optional*
         */
        @JsonProperty(value = "group_chat_created", required = false) val groupChatCreated: Boolean? = null,
        /**
         * Service message: the supergroup has been created.
         * This field can't be received in a message coming through updates,
         * because bot can't be a member of a supergroup when it is created.
         * It can only be found in reply_to_message if someone replies to
         * a very first message in a directly created supergroup.
         *
         * *Optional*
         */
        @JsonProperty(value = "supergroup_chat_created", required = false) val supergroupChatCreated: Boolean? = null,
        /**
         * Service message: the channel has been created.
         * This field can't be received in a message coming through updates,
         * because bot can't be a member of a channel when it is created.
         * It can only be found in reply_to_message if someone replies to a very first message in a channel.
         *
         * *Optional*
         */
        @JsonProperty(value = "channel_chat_created", required = false) val channelChatCreated: Boolean? = null,
        /**
         * Service message: auto-delete timer settings changed in the chat
         *
         * *Optional*
         */
        /* TODO: Add support for :@JsonProperty(
                value = "message_auto_delete_timer_changed",
                required = false
        ) val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged?, */
        /**
         * The group has been migrated to a supergroup with the specified identifier.
         *
         * *Optional*
         */
        @JsonProperty(value = "migrate_to_chat_id", required = false) val migrateToChatId: Long? = null,
        /**
         * The supergroup has been migrated from a group with the specified identifier.
         *
         * *Optional*
         */
        @JsonProperty(value = "migrate_from_chat_id", required = false) val migrateFromChatId: Long? = null,
        /**
         * Specified message was pinned.
         * Note that the Message object in this field will not contain
         * further [replyToMessage] fields even if it is itself a reply.
         *
         * *Optional*
         */
        @JsonProperty(value = "pinned_message", required = false) val pinnedMessage: Message? = null,
        /**
         * Message is an invoice for a payment, information about the invoice.
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "invoice", required = false) val invoice: Invoice?,
        /**
         * Message is a service message about a successful payment, information about the payment.
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "successful_payment", required = false) val successfulPayment: SuccessfulPayment?,
        /**
         * The domain name of the website on which the user has logged in.
         *
         * *Optional*
         */
        @JsonProperty(value = "connected_website", required = false) val connectedWebsite: String? = null,
        /**
         * Telegram Passport data
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "passport_data", required = false) val passportData: PassportData?,
        /**
         * Service message. A user in the chat triggered another user's
         * proximity alert while sharing Live Location.
         *
         * *Optional*
         */
        /* TODO: Add support for: @JsonProperty(
                value = "proximity_alert_triggered",
                required = false
        ) val proximityAlertTriggered: ProximityAlertTriggered?, */
        /**
         * Service message: video chat scheduled
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "video_chat_scheduled", required = false) val videoChatScheduled: VideoChatScheduled?,
        /**
         * Service message: video chat started
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "video_chat_started", required = false) val videoChatStarted: VideoChatStarted?,
        /**
         * Service message: video chat ended
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "video_chat_ended", required = false) val videoChatEnded: VideoChatEnded?,
        /**
         * Service message: new participants invited to a video chat
         *
         * *Optional*
         */
        /*TODO: Add support for: @JsonProperty(
                value = "video_chat_participants_invited",
                required = false
        ) val videoChatParticipantsInvited: VideoChatParticipantsInvited?,*/
        /**
         * Service message: data sent by a Web App
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "web_app_data", required = false) val webAppData: WebAppData?,
        /**
         * Inline keyboard attached to the message.
         * *login_url* buttons are represented as ordinary *url* buttons.
         *
         * *Optional*
         */
        //TODO: Add support for: @JsonProperty(value = "reply_markup", required = false) val replyMarkup: InlineKeyboardMarkup?
)