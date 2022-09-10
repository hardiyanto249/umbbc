package com.real.umbbc.bo;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RequestHttpSiteBO {
	private static final Logger mLogger = Logger
			.getLogger(RequestHttpSiteBO.class);

	private static int REQUEST_TIMEOUT_DEFAULT = 2000;
	private static int CONNECT_TIMEOUT_DEFAULT = 3000;
	private static int TIMEOUT_INTERVAL_DEFAULT = 20000;
	private static int CONNECTION_PERHOST_DEFAULT = 20;
	private static int MAX_CONNECTION_DEFAULT = 40;

	private final HttpConnectionManager connectionManager;
	private final HttpClient httpClient;
	private int requestTimeout;
	private int connectTimeout;
	private int timeoutInterval;

	public RequestHttpSiteBO() {
		this(REQUEST_TIMEOUT_DEFAULT, CONNECT_TIMEOUT_DEFAULT,
				TIMEOUT_INTERVAL_DEFAULT, CONNECTION_PERHOST_DEFAULT,
				MAX_CONNECTION_DEFAULT);
	}

	public RequestHttpSiteBO(int requestTimeout, int connectTimeout,
			int timeoutInterval, int connectionPerHost, int maxConnection) {
		connectionManager = new MultiThreadedHttpConnectionManager();

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(connectTimeout);
		params.setDefaultMaxConnectionsPerHost(connectionPerHost);
		params.setMaxTotalConnections(maxConnection);
		connectionManager.setParams(params);

		IdleConnectionTimeoutThread idleConnectionTimeoutThread = new IdleConnectionTimeoutThread();
		idleConnectionTimeoutThread.setTimeoutInterval(timeoutInterval);
		idleConnectionTimeoutThread.start();
		idleConnectionTimeoutThread.addConnectionManager(connectionManager);

		httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setConnectionManagerTimeout(connectTimeout);

		setRequestTimeout(requestTimeout);
		setConnectTimeout(connectTimeout);
		setTimeoutInterval(timeoutInterval);

	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int timeout) {
		this.requestTimeout = timeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getTimeoutInterval() {
		return timeoutInterval;
	}

	public void setTimeoutInterval(int timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	public String getResult(String url) throws Exception {
		return getResult(url, this.requestTimeout);
	}

	public String getResult(String url, int timeout) throws Exception {
		return getResult(url, this.requestTimeout, true);
	}

	/**
	 * 
	 * @param url
	 * @param timeout
	 * @param checkEmptyResponse
	 *            true if to throw exception on empty response
	 * @return
	 * @throws Exception
	 */
	public String getResult(String url, int timeout, boolean checkEmptyResponse)
			throws Exception {
		String result = "";
		if (timeout <= 0) {
			timeout = getRequestTimeout();
		}
		HttpMethod method = new GetMethod(url.trim());
		method.getParams().setSoTimeout(timeout);
		try {
			long startTime = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpURLConnection.HTTP_OK) {
				mLogger.error("Result " + statusCode + " for url " + url);
			}
			result = method.getResponseBodyAsString();
			long endTime = System.currentTimeMillis();
			mLogger.debug("Total time: " + (endTime - startTime));
		} catch (IOException e) {
			mLogger.error(e);
		} finally {
			method.releaseConnection();
		}

		return result;
	}
}
