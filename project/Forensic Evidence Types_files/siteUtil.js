<!DOCTYPE html>
<HTML lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<SCRIPT type="text/javascript">
	var path = window.location.pathname;
	var i=0;
	var ch='';
	while (i <= path.length){
		character = path.charAt(i);
		if (!isNaN(character * 1)){

		}else{
			if (character.match(/^[A-Z]*$/)) {
				newLoc = window.location.toString();
				newLoc = newLoc.toLowerCase();
				//alert ('upper case true '+window.location.pathname + ' -> ' + newLoc);
				window.location = newLoc;
			}
		}
		i++;
	}
</SCRIPT>
<META content="text/html; charset=utf-8" http-equiv="Content-Type">
<META content="IE=Edge" http-equiv="X-UA-Compatible">
<TITLE>SOM - 404 
Page</TITLE>
<STYLE type="text/css">
* {
	margin: 0;
	padding: 0;
}
p {
	margin:1em 0em
}
A {
	text-decoration:none;
	color:#000
}
a:hover {
	color:#666
}
BODY {
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	font-size:16px;
	background-color : #eeeeee;
}
#container {
	MARGIN: 5px auto;
	width:99%;
	max-WIDTH: 960px;
	FONT-SIZE: 87.5%;
	overflow:hidden;
	background-color:#fff;/*box-shadow: 1px 1px 15px 3px #000;
	border-radius:10px*/
}
#container #layoutArea, #container #footerArea {
	background-color:#fff;
	width:100%;
	height:auto
}
#container .WidthScreen {
	width:100%
}
/*---------SUPER TOP NAVIGATION LINKS ABOVE THE BANNER AREA---------*/
	#superTop {
	display:block;
	width:100%;
	height:40px;
}
ul#bannerLinks li {
	text-align: left;
	float: left;
	DISPLAY: BLOCK;
}
#superTop a {
	text-decoration: none;
	PADDING: 10px;/*font-weight:bold*/
}
#superTop a:hover {
	background-color:#fff;
}
/* ----BANNER AREA------- */

#bannerArea {
	width:100%;
	float:right;
	display:block;
	overflow:hidden;
	white-space:nowrap;
	vertical-align:bottom;
}
#siteBanner img {
}
/*----migov image----*/
/* -----SUB TOP NAVIGATION CONTAINS THE TOP NAV LINKS-----*/

/*FOOTER*/
#footerArea li {
	display:inline-block;
	margin-top:10px;
	float:left;
}
#footerArea a:hover {
	color:#999
}
/*RIGHT LINKS*/




#searchForm {
	position:absolute;
	top:0px;
	z-index:900;
	display:block;
	width:200px;
	height:40px
}
/*RIGHT NAV STYLES*/


#siteBanner {
	float:left;
	z-index:100;
	display:inline-block;
	position:relative;
	width:100%
}
#migovBrand {
	z-index:600;
	display:none;
	position:relative;
}
#migovBrand img {
}
#siteBanner img {
/*width:100%;*/
}
span.siteBannerTxt {
	display:none;
	color:#fff;
	font-size:120%
}
/*-- Search Styles -- */
#topNavSearch {
	width: 40%;
	height: 30px;
	padding: 2px;
	overflow: hidden;
	float:right;
	min-width:150px;
	max-width:850px;
	display:inline;
}
#query {
	padding: 5px 3px;
	height: 18px;
	width: 80%;
	border: 1px solid #a4c3ca;
	font: normal 13px 'trebuchet MS', arial, helvetica;
	background: #fff;
	-moz-border-radius:3px 3px 3px 3px;
	border-radius: 3px 3px 3px 3px;/*-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0 rgba(255, 255, 255, 1);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0 rgba(255, 255, 255, 1);
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0 rgba(255, 255, 255, 1);*/
}
#go:hover {
	background: #95d788;
	background-image: url(http://mi.gov/images/searchIconWhite.png);
}
#go {
	background: #6cbb6b;
	border-radius: 5px;
	border-width: 0px;
	height: 30px;
	margin: 1px;
	padding: 0;
	width: 30px;
	cursor: pointer;
	color: transparent;
	background-image: url(http://mi.gov/images/searchIconWhite.png);
	background-repeat: no-repeat;
	background-size: 70%;
	background-position: 50% 50%;
	_font-size: 0px;
}
#go:active {
	background: #95d788;
	outline: none;/*-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;*/
}
 #go::-moz-focus-inner {
 border: none;
}
 #query::-webkit-input-placeholder {
 color: #9c9c9c;
 font-style: italic;
}
 #query:-moz-placeholder {
 color: #9c9c9c;
 font-style: italic;
}
#query.placeholder {
	color: #9c9c9c !important;
	font-style: italic;
}
#query:focus {
	border-color: #8badb4;
	background: #fff;
	outline: none;
}
* {
	box-shadow:none;
	border-radius: 0px
}

/*MEDIA QUERIES FOR WIDTH--*/
@media screen and (max-width: 959px) {
}
 @media screen and (min-width: 768px) and (max-width: 960px) {
.page.DW {
	width:60%;
	margin:0 1%;
}
#topNavSearch {
	position: relative;
}
#topnav {
	display:none;
}
}
@media screen and (max-width: 768px) and (min-width: 480px) {
#migovBrand {
	display:none;
	position:absolute;
	top:-9999px
}
#siteBanner {
	float:left;
	z-index:100;
	display:inline-block;
	position:relative;
	width:100%
}
#rightNav {
	width:30%;
	float:right;
}
}
@media only screen and (max-width: 700px) {
#main {
	width:95%;
	float:none;
}
}
@media only screen and (max-width: 480px) {
#siteBanner {
	float:left;
	z-index:100;
	display:block;
	position:relative;
	width:100%;
	height:60px;
	height:auto;
	overflow:hidden;
	background-color:#958CA0;
}
span.siteBannerTxt {
	display:none;
	padding:1em;
	width:80%;
	white-space:normal
}
#main {
	width:95%;
	float:none;
}
.page.DW .SW {
	width: 95%;
	float: none;
}
}
@media only screen and (max-width: 320px) {
}
@media only screen and (max-width: 240px) {
}
</STYLE>
<LINK rel="stylesheet" type="text/css" href="http://www.michigan.gov/documents/som/SOM_2013_STYLES_RD_419101_7.css">
<STYLE>
	div.footer_box{width:23%}
</STYLE>
<META name="GENERATOR" content="MSHTML 9.00.8112.16533">
</HEAD>
<BODY>
<NOSCRIPT>
Browsers that can not handle javascript will not be able to 
access some features of this site.&lt;br&gt;
</NOSCRIPT>
<DIV id="container"><!-- Coremetrics Begin --> 
  <SCRIPT language="JavaScript" type="text/javascript" src="http://libs.coremetrics.com/eluminate.js"></SCRIPT> 
  <SCRIPT type="text/javascript" src="http://www.michigan.gov/coremetrics/cmcustom.js"></SCRIPT> 
  <SCRIPT type="text/javascript">
		var errorURL=document.URL;
		var serverHost = window.location.hostname.replace("www.","");
    	cmSetClientID("90259631",true,"data.coremetrics.com",serverHost);cmCreatePageviewTag(errorURL,"404 Error Page");
    </SCRIPT> 
  <!-- Coremetrics End -->
  <div id='superTop' loc='1'>
    <ul id='bannerLinks'>
      <li><a href='/departments' title='Agencies'>Agencies</a></li>
      <li><a href='/services' title='Online Services'>Online Services</a></li>
      <li><a href='/directories' title='Directories'>Directories</a></li>
      <li><a href='/socialmedia' title='Social Media'>Social Media</a></li>
      <li><a href='/somhelp' title='Help Center'>Help Center</a></li>
    </ul>
    <span class='migovlink'><a href='/som' title='MI Gov Home'>MI.gov</a></span></div>
  <div id='screenwidth'></div>
  <div id="sommasthead">
    <div id="migovlogo2"> <a href="/"><img src="http://www.mi.gov/images/migov.png" border="0" alt="michigan.gov"></a> </div>
  </div>
  <DIV id="topNavSearch">
    <FORM id="usasearchform" onsubmit="location.href='http://search.michigan.gov/search?affiliate=mi-som&amp;query=' + document.getElementById('query').value;return false;">
      <INPUT 
accessKey="s" id="query" class="usagov-search-autocomplete" title="Search" name="query" 
type="TEXT" placeholder="Search" autocomplete="off">
      <INPUT id="go" onclick="location.href='http://search.michigan.gov/search?affiliate=mi-som&amp;query=' + document.getElementById('query').value;return false;" name="fpSearchSubmitBtn" value="Go" type="submit">
      <SCRIPT type="text/javascript">
						//<![CDATA[
						var usasearch_config = { siteHandle:"mi-som" };
						var script = document.createElement("script");
						script.type = "text/javascript";
						script.src = "http://search.usa.gov/javascripts/remote.loader.js";
						document.getElementsByTagName("head")[0].appendChild(script);
						
						//]]>
						</SCRIPT>
    </FORM>
  </DIV>
  <DIV style="padding: 20px; width: 100%; height: auto; clear: all;margin-top:40px; float: left; display: block;" >
    <H3>PAGE&nbsp;NOT&nbsp;FOUND</H3>
    <P>We're sorry, but we're unable to locate the page you requested.
    <P>The page may have been removed, renamed, or deleted.</P>
    <P>You can try searching for your page using the search bar above, or choosing 
      from one of the frequently-searched items below.</P>
    <ul style="margin-left:25%">
      <li><A href="http://michigan.gov/som/0,1607,7-192-29929---A,00.html">List of State Web Sites</A></li>
      <li><A href="http://michigan.gov/snyder">Governor Snyder</A></li>
      <li><A href="http://michigan.gov/unemployment">Unemployment Assistance</A></li>
      <li><A href="http://agency.governmentjobs.com/michigan/default.cfm">Jobs</A></li>
      <li><A href="http://michigan.gov/lottery">Lottery Numbers</A></li>
    </ul>
  </DIV>
  
  <!--BEGIN SOM FOOTER INCLUDE--->
  <DIV style="clear: both;MARGIN:10PX; padding-top:10px;border-top:1px dashed #ddd">
    <DIV id="help_container" class="footer_box">
      <H4>Help</H4>
      <DIV>
        <P><A title="How do I...?" href="http://michigan.gov/som/0,1607,7-192-29701-146--FI,00.html">How 
          do I...?</A></P>
        <P><A title="Search MI.GOV" href="http://search.michigan.gov/search?affiliate=mi-som&amp;query=">Search 
          MI.GOV</A></P>
        <P><A title="Live Help Center" href="http://michigan.gov/som/0,1607,7-192-31940-195707--FI,00.html">Live 
          Help Center</A></P>
        <P><A title="About this Site" href="http://michigan.gov/som/0,1607,7-192-46428---FI,00.html">About 
          this Site</A></P>
        <P><A title="Site Map" 
href="http://michigan.gov/som/0,1607,7-192----SM,00.html">Site Map</A></P>
        <P><A title="Contact the Webmaster" href="http://michigan.gov/som/0,1607,7-192--74476--,00.html">Contact 
          the Webmaster</A></P>
      </DIV>
    </DIV>
    <DIV id="contacts_container" class="footer_box">
      <H4>Contacts</H4>
      <DIV>
        <P><A title="Contact the Governor" href="http://michigan.gov/snyder/0,4668,7-277-57827-267869--,00.html" 
target="_new">Contact the Governor</A></P>
        <P><A title="Departments &amp; Agencies" href="http://michigan.gov/som/0,1607,7-192-29701_29702_30045---FI,00.html">Departments 
          &amp; Agencies</A></P>
        <P><A title="Employee Lookup" 
href="http://www.state.mi.us/dit/directory.aspx">Employee Lookup</A></P>
        <P><A title="State Phone Book" href="http://michigan.gov/som/0,1607,7-192-29701-113777--FI,00.html">Phone 
          Book</A></P>
        <P><A title="Find Your Representative" 
href="http://www.house.mi.gov/mhrpublic/">Find Your Representative</A></P>
        <P><A title="Find Your Senator" href="http://www.senate.michigan.gov/fysenator/fysenator.htm">Find 
          Your Senator</A></P>
        <P><A title="Michigan Courts" href="http://michigan.gov/som/0,1607,7-192-29701_29703---FI,00.html">Michigan 
          Courts</A></P>
        <P><A title="Local Governments" href="http://michigan.gov/som/0,1607,7-192-29701_31713_31714---,00.html">Local 
          Governments</A></P>
      </DIV>
    </DIV>
    <DIV id="social_network_container" class="footer_box">
      <H4>Stay Connected</H4>
      <DIV align="left"><A title="Michigan on Facebook" href="http://michigan.gov/socialmedia"><IMG 
border="0" alt="Michigan on Facebook" src="/images/global/facebookIcon.png"></A><A 
title="Michigan on Twitter" href="http://michigan.gov/socialmedia"><IMG border="0" 
alt="Michigan on Twitter" src="/images/global/twitterIcon.png"></A><BR>
        <A title="Michigan on YouTube" 
href="http://michigan.gov/socialmedia"><IMG border="0" alt="Michigan on YouTube" 
src="/images/global/youtubeIcon.png"></A><A title="Get Email and Text Updates from gov Delivery!" 
href="http://michigan.gov/socialmedia"><IMG border="0" alt="Get Email and Text Updates from gov Delivery!" 
src="/images/global/govdeliveryIcon.png"></A>
        <P><A href="http://michigan.gov/lottery">Get Lottery Drawing 
          Information!</A></P>
        <P><A href="http://michigan.gov/minewswire">Get the Latest News!</A></P>
        <P><A href="https://public.govdelivery.com/accounts/MIGOV/subscriber/new">Sign 
          Up for Email and Texts!</A></P>
      </DIV>
    </DIV>
    <DIV id="milocator" class="footer_box">
      <H4>Michigan Locator</H4>
      <DIV><SPAN>Search any location for State     government services, state parks 
        and recreation, or educational     entities.<BR>
        <INPUT style="width: 110px; height:20px;margin-top: 10px;" 
id="txtLocationSearch" onfocus="this.value=''" title="Enter city or ZIP" 
onkeypress="checkKey(event)" name="location" value="Enter city or ZIP">
        <INPUT 
style="top: 5px; position: relative;" title="Go" onclick="launchMiLocator('')" 
name="btnLocationSearch" alt="GO" src="/images/som/SOM_SEARCH_GO_322960_7.gif" 
type="image">
        </SPAN></DIV>
    </DIV>
    <!--END SOM FOOTER INCLUDE--->
    <style>
	#footer {text-align: center;font-size:90%}
    #footer a{display:inline-block;width:auto;margin:3px 10px;color:#000; text-decoration:none}	
    </style>
    <DIV id='footer' style="width: 100%; clear:both; background-color:#eeeeee;"> 
      
      <!-- agyHorizontalLinks BOTTOM_NAV_LINKS --> 
      <A href="http://michigan.gov/">Michigan.gov Home</A>
      </LI>
      <A id="cat_32874" href="http://michigan.gov/som/0,4669,7-192-32874-146--,00.html"> Help &amp; Contacts</A> <A id="cat_53687" href="http://michigan.gov/disabilityresources" target="_new"> Disability Resources</A> <A id="cat_41470" href="http://michigan.gov/som/0,4669,7-192-41470-142612--,00.html"> Awards</A> <A id="cat_55584" href="http://michigan.gov/som/0,1607,7-192-39925---,00.html"> Foreign Languages</A> <A id="cat_31939" href="http://michigan.gov/som/0,4669,7-192-31939---SM,00.html"> Site Map </A> <A id="cat_39487" href="http://michigan.gov/som/0,1607,7-192-29929---A,00.html"> State Web Sites</A> <A id="cat_60128" href="http://michigan.gov/lara/0,4601,7-154-35738---,00.html"> Office of Regulatory Reinvention</A> <A id="cat_64396" href="http://michigan.gov/openmichigan/0,4648,7-266-58520---,00.html"> Spending &amp; Accountability</A> <BR>
      <br>
      <A id="cat_26913" href="http://michigan.gov/som/0,4669,7-192-26913-2090--,00.html"> Accessibility Policy</A> <A id="cat_26914" href="http://michigan.gov/som/0,4669,7-192-26914-2088--,00.html"> Privacy Policy</A> <A id="cat_26915" href="http://michigan.gov/som/0,4669,7-192-26915-2089--,00.html"> Link Policy</A> <A id="cat_26916" href="http://michigan.gov/som/0,4669,7-192-26916-2301--,00.html"> Security Policy</A> <A href="http://michigan.gov/minewswire" target="_new">Michigan 
      News</A> <A 
  href="http://michigan.gov/som/0,4669,7-192--115596--,00.html">Michigan.gov 
      Survey</A> <BR>
      <BR>
      <SPAN class="copyright"><BR>
      Copyright © 2001-2014 State 
      of Michigan</SPAN></DIV>
  </DIV>
</DIV>
</BODY>
</HTML>
