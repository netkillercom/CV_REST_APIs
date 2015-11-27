package com.collavate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class App{
    
	public static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth" ;
	public static final String OAUTH_TOKEN_URL = "https://accounts.google.com/o/oauth2/token" ;
		
	public static final String CLIENT_ID = "Paste your client id here";
	public static final String CLIENT_SECRET = "Paste your client secret here";
	
	private static final String CALLBACK="https://google.co.in";
	private static String accessToken;
	
	
	public static void main(String[] args) throws Exception {
		accessToken=getAccessToken();
		
		System.out.println("Enter the method name you want to execute  : ");
		
		Scanner sc=new Scanner(System.in);
		String methodName=sc.nextLine();
		try {
			Method method=App.class.getMethod(methodName, null);
			method.invoke(null);
		}
		catch(NoSuchMethodException ex)
		{
			System.out.println("No method exists with the given name.");
		}
	}
	
   /**
   * Get shared templates for the account
   *
   * <p>
   * Prints the metadata for shared templates.
   * <p>
   */
   public static void getSharedTemplates() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/templates/v2/shared/";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get templates created by account owner
	*
	* <p> 
	* Prints the metadata for the templates created by account owner.
	* <p>
	*/
	public static void getOwnTemplates() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/templates/v2/owned/";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get references for the current account
	*
	* <p> 
	* Prints the metadata for the references for the current account.
	* <p>
	*/
	public static void getReferences() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/shared/references";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get rejected processes shared with the current account
	*
	* <p> 
	* Prints the metadata for the rejected processes shared with the current account.
	* <p>
	*/
	public static void getInboxRejectedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/shared/rejected";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get processes with status to be reviewed for the current account
	*
	* <p> 
	* Prints the metadata for the processes with status to be reviewed for the current account.
	* <p>
	*/
	public static void getInboxToBeReviewedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/shared/to-be-reviewed";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get template for the given template id
	*
	* <p> 
	* Prints metadata of the template for the given template id.
	* <p>
	*/
	public static void getTemplateDoc() throws Exception{
		String templateId = getInput("Enter template id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/templates/v2/"+templateId;
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get process for the given process id
	*
	* <p> 
	* Prints metadata of the process for the given process id.
	* <p>
	*/
	public static void getProcessDoc() throws Exception{
		String processId = getInput("Enter process id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/"+processId;
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Rejects process for the given process id
	*
	* <p> 
	* Rejects process for the given process id and prints its metadata.
	* <p>
	*/
	public static void rejectDoc() throws Exception{
		String processId = getInput("Enter process id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/"+processId+"/reject-or-disagree";
		
		HttpPut request=new HttpPut(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Approve process for the given process id
	*
	* <p> 
	* Approve process for the given process id and prints its metadata.
	* <p>
	*/
	public static void approveDoc() throws Exception{
		String processId = getInput("Enter process id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/"+processId+"/approve-or-agree";
		
		HttpPut request=new HttpPut(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Delete process (Drafts only) for the given process id
	*
	* <p> 
	* Deletes process (Drafts only) for the given process id and prints its metadata.
	* <p>
	*/
	public static void deleteDraft() throws Exception{
		String processId = getInput("Enter process id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/"+processId;
		
		HttpDelete request=new HttpDelete(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Reset process for the given process id
	*
	* <p> 
	* Reset process for the given process id and prints its metadata.
	* <p>
	*/
	public static void resetDoc() throws Exception{
		String processId = getInput("Enter process id : ");
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/"+processId+"/reset?comment=test";
		
		HttpPut request=new HttpPut(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get processes created by the account owner
	*
	* <p> 
	* Prints metadata for the processes created by the account owner.
	* <p>
	*/
	public static void getMyProcessDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/owned";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get submitted processes
	*
	* <p> 
	* Prints metadata for the submitted processes.
	* <p>
	*/
	public static void getSubmittedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/owned/submitted";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get drafted processes
	*
	* <p> 
	* Prints metadata for the drafted processes.
	* <p>
	*/
	public static void getDraftedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/owned/draft";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get rejected processes owned by account owner
	*
	* <p> 
	* Prints metadata for the rejected processes owned by account owner.
	* <p>
	*/
	public static void getRejectedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/owned/rejected";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get in-progress processes shared with account owner
	*
	* <p> 
	* Prints metadata for the in-progress processes shared with account owner.
	* <p>
	*/
	public static void getInProgressDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/shared/in-progress";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get approved processes shared with account owner
	*
	* <p> 
	* Prints metadata for the approved processes shared with account owner.
	* <p>
	*/
	public static void getInboxApprovedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/shared/approved";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	/**
	* Get approved processes owned by account owner
	*
	* <p> 
	* Prints metadata for the approved processes owned by account owner.
	* <p>
	*/
	public static void getApprovedDocs() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		String url="https://netkiller-cea.appspot.com/_ah/api/collavate/v2/owned/approved";
		HttpGet request=new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken );
		HttpResponse response=client.execute(request);
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		System.out.println("result = "+getResultString(response.getEntity().getContent()));
	}
	
	private static String getAccessToken() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		System.out.println("AuthUrl = "+getAuthorizatoinUrl(getScopes(), CALLBACK));
		
		Scanner in = new Scanner(System.in);
		String authCode=in.nextLine();
        
        HttpPost post = new HttpPost(OAUTH_TOKEN_URL);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
    	urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
    	urlParameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
    	urlParameters.add(new BasicNameValuePair("redirect_uri", CALLBACK));
    	urlParameters.add(new BasicNameValuePair("code", authCode));
    	
    	post.setEntity(new UrlEncodedFormEntity(urlParameters));

    	HttpResponse response = client.execute(post);
    	JSONObject jsonObject=new JSONObject(getResultString(response.getEntity().getContent()));
    	return jsonObject.get("access_token").toString();
	}
	
	private static String getResultString(InputStream in) throws IOException{
		BufferedReader rd = new BufferedReader(
    	        new InputStreamReader(in));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
		return result.toString();
	}
	
	private static String getScopes() {
        String allScopeStr = "https://www.googleapis.com/auth/userinfo.email " +
                "https://www.googleapis.com/auth/userinfo.profile " +
                "https://docs.google.com/feeds/ " +
                "https://docs.googleusercontent.com/feeds/download/ " +
                "https://spreadsheets.google.com/feeds/ " +
                "https://www.googleapis.com/auth/drive " +
                "https://www.google.com/m8/feeds/";
        return allScopeStr;
    }
	
	private static String getAuthorizatoinUrl(String scope, String callback){
		String url = AUTH_URL 
				+ "?scope=" + scope 
				+  "&response_type=code&access_type=online&approval_prompt=force" 
				+ "&client_id=" + CLIENT_ID
				+ "&redirect_uri=" +CALLBACK
				;
		
		return url;
	}
	
	private static String getInput(String message)
	{
		System.out.println(message);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}
