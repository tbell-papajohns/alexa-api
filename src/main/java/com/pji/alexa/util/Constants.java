package com.pji.alexa.util;

public class Constants {	
	public static final boolean BOOLEAN_TRUE= true;
	public static final boolean BOOLEAN_FALSE= false;

	public static final String INTERACTION_NEXT_STEP="nextstep";
	public static final String INTERACTION_STEP_ORDER_TYPE="ordertype";
	public static final String INTERACTION_STEP_ADDRESS="address";
	public static final String INTERACTION_STEP_FAVORITE="favorite";
	public static final String INTERACTION_STEP_RECENT_ORDER="recent";
	public static final String INTERACTION_STEP_ORDER_CONFIRM="orderconfirm";
	public static final String INTERACTION_STEP_CARD="card";
	public static final String INTERACTION_STEP_CART_CONFIRM="cartconfirm";
	public static final String INTERACTION_STEP_ORDER="order";


	public static final String ORDER_TYPE_FAVORITE="favorite";
	public static final String ORDER_TYPE_RECENT="recentOrder";
	public static final String ORDER_TYPE_NEW_ORDER="new";
	public static final String ORDER_TYPE_BOTH="both";
	public static final String ORDER_NUMBER="orderNumber";
	public static final String SUBSCRIPTION_ID="subscriptionId";
	
	
	/**
	 * Below object key would represent the object that store all important values to place an order
	 */
	public static final String SESSION_ORDER_OBJECT="sessionOrderObject";
	public static final String SESSION_ORDER_ITEM="item";
	public static final String SESSION_USER_DATA="userdata";
	public static final String SESSION_ORDER_CONFIRMED_FLAG="orderconfirmed";
	public static final String SESSION_CART_CONFIRMED_FLAG="cartconfirmed";	
	
	public static final String CONFIG_VERBAGE_CLOUD_STORAGE_BUCKET_NAME="verbage.bucket.name";
	public static final String CONFIG_VERBAGE_CLOUD_STORAGE_FILE_NAME="verbage.file.name"; 
	public static final long CONFIG_VERBAGE_RELOAD_TIME= 1000;
	
	public static final String ALEXA_DISCOUNT_PROMOCODE="pji.alexa.promocode";
	public static final String ALEXA_ORDER_TYPE="DELIVERY";
	public static final String ALEXA_DELIVERY_ROOM_TYPE="NON";
	public static final String ALEXA_DELIVERY_TYPE="HOME";
	public static final String ALEXA_PAYMENT_TYPE_CASH="cash";
	public static final String ALEXA_PAYMENT_TYPE_CARD="card";

		
	public static final String VERBAGE_DISCOUNT_PERCENT="pji.alexa.order.discount";
	public static final String VERBAGE_START_OVER="verbage.start.over";
	public static final String VERBAGE_CLEAR_CART="verbage.clear.cart";
	public static final String VERBAGE_CLEAR_CART_NO_ITEMS="verbage.clear.cart.no.items";
	
	public static final String VERBAGE_ERROR_RESPONSE_FOR_NO_OTHER_FUNCTIONALITY="verbage.error.response";
	public static final String VERBAGE_ERROR_RESPONSE_FOR_API_EXCEPTIONS="verbage.api.exception.response";
	
	public static final String VERBAGE_ERROR_RESPONSE_FOR_STORE_EXCEPTIONS="verbage.api.store.exception.response";
	
	public static final String VERBAGE_WELCOME_MESSAGE_DEFAULT="verbage.welcome.message.default";
	public static final String VERBAGE_WELCOME_MESSAGE_LINK_ACCOUNT="verbage.welcome.message.link.account";
	public static final String VERBAGE_WELCOME_MESSAGE_DISCOUNT="verbage.welcome.message.discount";	
	public static final String VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST="verbage.welcome.message.favorite.exist";
	public static final String VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST="verbage.welcome.message.lastorder.exist";
	public static final String VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST="verbage.welcome.message.both.favorite.and.lastorder.exist";
	public static final String VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_DONT_EXIST="verbage.welcome.message.both.favorite.and.lastorder.not.exist";
	

	public static final String VERBAGE_PAST_ORDER_DETAILS= "verbage.past.order.details";
	
	public static final String VERBAGE_ORDER_TYPE_NOT_CONFIRMED= "verbage.order.type.not.confirmed";
	
	public static final String VERBAGE_ADDRESS_LISTING_FIRST_TIME="verbage.address.list.first.time";
	public static final String VERBAGE_ADDRESS_LISTING_AFTER_INVALID_RESPONSE="verbage.address.list.after.invalid.response";
	public static final String VERBAGE_ADDRESS_LISTING_AFTER_NO_RESPONSE="verbage.address.list.after.no.response";
	public static final String VERBAGE_ADDRESS_LISTING_WHEN_NO_ADDRESS_EXIST="verbage.address.list.when.no.more.exist";

	/**
	 * Fvorite Options Verbiages
	 */
	public static final String VERBAGE_FAVORITE_LISTING= "verbage.favorite.list";
	public static final String VERBAGE_FAVORITE_LISTING_AFTER_INVALID_RESPONSE= "verbage.favorite.list.after.invalid.response";
	public static final String VERBAGE_FAVORITE_LISTING_AFTER_VALID_RESPONSE= "verbage.favorite.list.valid.response";
	public static final String VERBAGE_FAVORITE_DETAILS= "verbage.favorite.details";
	public static final String VERBAGE_FAVORITE_ITEMS_DESC= "verbage.favorite.items.desc";
	
	/**
	 * Payment Options Verbiages
	 */
	public static final String VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONLY_CASH="verbage.payment.list.first.time.only.cash";
	public static final String VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONE_CARD="verbage.payment.list.first.time.one.card";
	public static final String VERBAGE_PAYMENT_LISTING_FIRST_TIME_MORE_CARD="verbage.payment.list.first.time.more.card";
	public static final String VERBAGE_PAYMENT_LISTING_FIRST_TIME="verbage.payment.list.first.time";
	public static final String VERBAGE_PAYMENT_LISTING_AFTER_INVALID_RESPONSE="verbage.payment.list.after.invalid.resp";
	public static final String VERBAGE_PAYMENT_NO_SELECTED_OPTION="verbage.payment.no.slected.option";
	
	public static final String VERBAGE_CARDS_LISTING_SUPPORTING_TEXTS_ONE=" one";
	public static final String VERBAGE_CARDS_LISTING_SUPPORTING_TEXTS_ONE_THAT_ENDS_WITH=" one ends with ";
	
	/**
	 * Order Confirmation
	 */
	public static final String VERBAGE_ORDER_CONFIRM_ITEMS_DESC= "verbage.order.confirm.items.desc";
	
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING="verbiage.cards.prompt.creation.string";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_OR="verbiage.cards.prompt.creation.string.or";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_IS="verbiage.cards.prompt.creation.string.is";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_APOSTROPHE="verbiage.cards.prompt.creation.string.apostrophe";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_NO="verbiage.cards.prompt.creation.string.no";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_DONT="verbiage.cards.prompt.creation.string.dont";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_ONE="verbiage.cards.prompt.creation.string.one";
	public static final String VERBIAGE_CARDS_PROMPT_CREATION_STRING_THE="verbiage.cards.prompt.creation.string.the";
	
	
	
	
	
	
	public static final String VERBAGE_FAVORITE_RECENT_PRODUCT_ITEM="verbiage.favorite.recent.product.item";
	public static final String VERBAGE_FAVORITE_RECENT_PRODUCT_ITEM_APPEND_S="verbiage.favorite.recent.product.item.append.s";
	public static final String VERBAGE_FAVORITE_RECENT_PRODUCT_ITEM_AND="and";
	
	public static final String VERBAGE_CART_NOT_CONFIRMED = "verbage.cart.not.confirmed";
	public static final String VERBAGE_ORDER_NOT_CONFIRMED = "verbage.order.not.confirmed";
	
	public static final String VERBAGE_ORDER_SUCCESS = "verbage.order.success";
	public static final String VERBAGE_ORDER_HELP = "verbage.order.help";
	public static final String VERBAGE_ORDER_CANCEL = "verbage.order.cancel";
	public static final String VERBAGE_ORDER_STOP = "verbage.order.cancel";
	public static final String VERBAGE_ORDER_EXIT = "verbage.order.cancel";
	public static final String VERBAGE_ORDER_READY_ESTIMATE = "verbage.order.ready.estimate";
	//ORDERS-440
	public static final String VERBAGE_ORDER_OUTOFMVP = "verbage.order.OutOfMVP";
	
	//ORDERS-430
	public static final String VERBAGE_ORDER_SUCCESS_FOR_CARD = "card.verbage.order.success";
	public static final String VERBAGE_STREET_ADDRESS = "verbage.street.address";
	public static final String VERBAGE_CARD_NUMBER = "verbage.card.number";
	public static final String VERBAGE_GRAND_TOTAL = "verbage.grand.total";
	public static final String VERBAGE_GRAND_TOTAL_IN_CASH = "verbage.grand.total.in.cash";
	
	public static final String USER_RESPONSE_TYPES_INVALID="invalidresponse";
	public static final String USER_RESPONSE_TYPES_NO="noresponse";
	public static final String USER_RESPONSE_TYPES_LAST_NO="lastno";
	
	public static final String PJI_ENDPOINT_ENVIRONMENT="pji.endpoint.environment";
	public static final String PJI_ENDPOINT_PROTOCAL="pji.endpoint.protocol";
	public static final String PJI_ENDPOINT_HOST="pji.endpoint.host";
	public static final String PJI_ENDPOINT_PORT="pji.endpoint.port";	
	public static final String PJI_ENDPOINT_HEADER_TOKEN="pji.endpoint.headers.pjauthorization";
	public static final String PJI_ENDPOINT_HEADER_NAME_CLIENT_APP="pji.endpoint.headers.client.appname";
	public static final String PJI_ENDPOINT_HEADER_VALUE_CLIENT_APP="pji.endpoint.headers.client.appvalue";
	public static final String PJI_SERVER_FILENAME="pji.server.filename";

	public static final String PJI_ENDPOINT_HEADER_AUTHORIZATION="Authorization";
	public static final String PJI_ENDPOINT_HEADER_AUTHORIZATION_TYPE="Basic";	
	public static final String PJI_ENDPOINT_METHOD_GET="pji.endpoint.method.get";
	public static final String PJI_ENDPOINT_METHOD_POST="pji.endpoint.method.post";	
	public static final String PJI_ENDPOINT_CUSTOMER_CARD_LIST_URL="pji.endpoint.customer.card.list.url";	
	public static final String PJI_ENDPOINT_STORE_SEARCH_URL="pji.endpoint.store.search.url";
	
	public static final String PJI_ENDPOINT_CUSTOMER_FAVORITES_LIST_URL="pji.endpoint.customer.favorites.list.url";	
	public static final String PJI_ENDPOINT_CUSTOMER_FAVORITE_URL="pji.endpoint.customer.favorite.url";
	
	public static final String PJI_ENDPOINT_CUSTOMER_PAST_ORDERS_URL = "pji.endpoint.customer.past.orders.url";

	public static final String PJI_ENDPOINT_ADD_CART_ITEMS_URL="pji.endpoint.add.cart.details.url";	
	public static final String PJI_ENDPOINT_CUSTOMER_DETAILS = "pji.endpoint.customer.details";
	public static final String PJI_ENDPOINT_SUBMIT_ORDER_DETAILS="pji.endpoint.submit.order.details";
	public static final String PJI_ENDPOINT_ORDER_READY_ESTIMATE="pji.endpoint.order.ready.estimate";
	
	public static final String PJI_OAUTH_JWT_BODY_CUSTOMERID="pji.oauth.jwt.body.customerid";
	public static final String PJI_OAUTH_JWT_BODY_PJTOKEN="pji.oauth.jwt.body.pjtoken";
	public static final String PJI_OAUTH_JWT_PUBLIC_CERT_FILE="pji.oauth.cert.file";

	public static final String DIALOG_STATE_STARTED="STARTED";
	public static final String DIALOG_STATE_IN_PROGRESS="IN_PROGRESS";
	public static final String DIALOG_STATE_COMPLETED="COMPLETED";
	
	public static final String INTENT_NAME_ORDER="intent.name.order";
	public static final String INTENT_NAME_START_OVER="intent.name.startover";
	public static final String INTENT_NAME_HELP="intent.name.help";
	public static final String INTENT_NAME_CLEAR="intent.name.clear";
	public static final String INTENT_NAME_CANCEL="intent.name.cancel";	
	public static final String INTENT_NAME_STOP="intent.name.stop";	
	public static final String INTENT_NAME_OUTOFMVP="intent.name.outofmvp";
	
	
	public static final String SLOT_VALUE_YES="YES";
	public static final String SLOT_VALUE_NO="NO";
	public static final String SLOT_VALUE_LAST		=	"last";
	
	public static final String SLOT_NAME_ADDRESS_CONFIRMATION="slot.name.address.confirmation";
	public static final String SLOT_NAME_ORDER_CONFIRMATION="slot.name.order.confirmation";
	public static final String SLOT_NAME_CART_CONFIRMATION="slot.name.cart.confirmation";
	public static final String SLOT_NAME_CARD_CONFIRMATION="slot.name.card.confirmation";
	public static final String SLOT_NAME_ORDER_TYPE_CONFIRMATION="slot.name.order.type.confirmation";
	public static final String SLOT_NAME_ORDER_TYPE="slot.name.OrderType";
	public static final String SLOT_NAME_ORDER_NAME="slot.name.OrderName";
	public static final String SLOT_NAME_PAYMENT_METHOD="slot.name.payment.method";
	
	//ORDERS-430
	public static final String ADDRESS_PROMPT_CARD_TITLE="verbiage.address.prompt.card.title";
	public static final String FAVOURITE_PROMPT_CARD_TITLE="verbiage.favourite.prompt.card.title";
	public static final String CREDIT_CARD_PROMPT_CARD_TITLE="verbiage.credit.card.prompt.card.title";
	public static final String ORDER_PROMPT_CARD_TITLE="verbiage.order.prompt.card.title";
	public static final String CART_PROMPT_CARD_TITLE="verbiage.cart.prompt.card.title";
	public static final String FAV_DETAILS_CARD_TITLE="verbiage.fav.details.prompt.card.title";
	public static final String CONFIRMED_ORDER_CARD_TITLE="verbiage.confirm.order.prompt.card.title";
	public static final String HELP_CONTENT_CARD_TITLE="verbiage.help.content.card.title";
	public static final String THANKYOU_CARD_TITLE="verbiage.thanks.card.title";
	public static final String CLEAR_CART_CARD_TITLE="verbiage.clear.cart.title";
	
	public static final String KEY_PASS = "keypass";
	public static final String ALIAS = "alias";
	
/*==========================================================ALEXA CARDS================================================================*/	
	public static final String ALEXA_CARD_ORDERTYPE_PROMPT_TITLE="verbiage.ordertype.prompt.card.title";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DISCOUNT="card.verbage.welcome.message.discount";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_EXIST="card.verbage.welcome.message.both.favorite.and.lastorder.exist";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_RECENT_ORDER_EXIST="card.verbage.welcome.message.lastorder.exist";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_FAVORITE_EXIST="card.verbage.welcome.message.favorite.exist";
	
	public static final String ALEXA_CARD_VERBAGE_ADDRESS_LISTING_FIRST_TIME="card.verbage.address.list.first.time";
	public static final String ALEXA_CARD_VERBAGE_ADDRESS_LISTING_AFTER_INVALID_RESPONSE="card.verbage.address.list.after.invalid.response";
	public static final String ALEXA_CARD_VERBAGE_ADDRESS_LISTING_AFTER_NO_RESPONSE="card.verbage.address.list.after.no.response";
	public static final String ALEXA_CARD_VERBAGE_ADDRESS_LISTING_WHEN_NO_ADDRESS_EXIST="card.verbage.address.list.when.no.more.exist";
	public static final String ALEXA_CARD_VERBAGE_ORDER_STOP="card.verbage.order.cancel";
	public static final String ALEXA_CARD_VERBAGE_ORDER_CANCEL="card.verbage.order.cancel";
	public static final String ALEXA_CARD_VERBAGE_START_OVER="card.verbage.start.over";
	public static final String ALEXA_CARD_VERBAGE_ORDER_OUTOFMVP="card.verbage.order.OutOfMVP";
	public static final String ALEXA_CARD_VERBAGE_ORDER_HELP="card.verbage.order.help";
	public static final String ALEXA_CARD_VERBAGE_CLEAR_CART_NO_ITEMS="verbage.clear.cart.no.items";
	public static final String ALEXA_CARD_VERBAGE_ERROR_RESPONSE_FOR_API_EXCEPTIONS="card.verbage.api.exception.response";
	public static final String ALEXA_CARD_VERBAGE_ERROR_RESPONSE_FOR_NO_OTHER_FUNCTIONALITY="card.verbage.error.response";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DEFAULT="card.verbage.welcome.message.default";
	public static final String ALEXA_CARD_VERBAGE_ORDER_SUCCESS="card.verbage.order.success";
	public static final String ALEXA_CARD_VERBAGE_GRAND_TOTAL="card.verbage.grand.total";
	public static final String ALEXA_CARD_VERBAGE_GRAND_TOTAL_IN_CASH="card.verbage.grand.total.in.cash";
	public static final String ALEXA_CARD_VERBAGE_FAVORITE_LISTING="card.verbage.favorite.list";
	public static final String PAST_ORDER_PROMPT_CARD_TITLE="verbiage.pastorder.prompt.card.title";
	public static final String ALEXA_CARD_VERBAGE_ORDER_NOT_CONFIRMED = "card.verbage.order.not.confirmed";
	public static final String ALEXA_CARD_VERBAGE_CART_NOT_CONFIRMED = "card.verbage.cart.not.confirmed";
	public static final String VERBAGE_WELCOME_MESSAGE_DEFAULT_NO_ADDRESS = "verbage.welcome.message.default.no.address";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_DEFAULT_NO_ADDRESS = "card.verbage.welcome.message.default.no.address";
	public static final String ALEXA_CARD_VERBAGE_WELCOME_MESSAGE_BOTH_FAVORITE_AND_RECENT_DONT_EXIST="card.verbage.welcome.message.both.favorite.and.lastorder.not.exist";
	public static final String ALEXA_CARD_VERBAGE_ORDER_READY_ESTIMATE = "card.verbage.order.ready.estimate";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_NO_SELECTED_OPTION="card.verbage.payment.no.slected.option";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONLY_CASH="card.verbage.payment.list.first.time.only.cash";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_ONE_CARD="card.verbage.payment.list.first.time.one.card";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME_MORE_CARD="card.verbage.payment.list.first.time.more.card";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_LISTING_FIRST_TIME="card.verbage.payment.list.first.time";
	public static final String ALEXA_CARD_VERBAGE_PAYMENT_LISTING_AFTER_INVALID_RESPONSE="card.verbage.payment.list.after.invalid.resp";
	public static final String ALEXA_CARD_VERBAGE_ORDER_CONFIRM_ITEMS_DESC= "card.verbage.order.confirm.items.desc";
	public static final String ALEXA_CARD_VERBAGE_FAVORITE_LISTING_AFTER_INVALID_RESPONSE= "card.verbage.favorite.list.after.invalid.response";
	public static final String ALEXA_CARD_VERBAGE_FAVORITE_LISTING_AFTER_VALID_RESPONSE= "card.verbage.favorite.list.valid.response";
	public static final String FAVOURITE_PAST_NO_EXIST_PROMPT_CARD_TITLE="verbiage.fav.past.no.exist.prompt.card.title";
	public static final String ALEXA_CARD_VERBAGE_ORDER_TYPE_NOT_CONFIRMED= "card.verbage.order.type.not.confirmed";
}
