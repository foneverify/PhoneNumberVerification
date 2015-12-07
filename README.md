# PhoneNumberVerification
Verifying the user phone number using APIs


Key Features of the solution
•	Smooth Integration
•	One solution for all: Foneverify works with all channels like Web, WAP & App
•	Instant Global Reach: Integration with Foneverify allows you to verify users across 200+ countries from day 1.
•	Automatic fall back: For example if a user does not enter the OTP in XX seconds the system would switchover to a fall back channel to complete the verification.
•	Flexible Call flows: You can choose the fall back to be SMS or Missed call
•	Predictable Cost: Our commercial model is very user friendly wherein we share the risk & charge only for successful verifications.




Call Flows
•	We have two calls flows
o	SMS – SMS: SMS Primary and SMS Fallback
o	SMS – VOICE: SMS Primary and Voice Fallback
o	Voice- Voice : Voice Primary and Voice Fallback

SMS: FoneVerify system allocates an OTP to this MSISDN and sends this OTP to the end user (MSISDN) via SMS. Client application picks or user manually types in the received OTP. Application sends back this OTP to the FoneVerify system

Voice: FoneVerify system allocates a DID (Direct Inward Dialling Number) to this MSISDN and sends this DID number to application in API’s response. The Application prompts the same to the End user. The end-user then manually gives (or application automatically initiates) a missed call to the allocated DID. 


•	Primary to Secondary Fallback –
Based on Application Vendor’s requirements one of the modes can be assigned as primary mode of verification and another as fallback. For e.g. a vendor could chose to have SMS as the primary mode of verification and voice as secondary. In such a scenario in case the SMS verification fails to happen in a predefined time window then the end user will be automatically switched to the voice verification flow & a DID number will be assigned to him. 




Solution Back-end
•	Ours is a REST API based solution which can be easily integrated in any call flows.
•	Once application is registered with FoneVerify account, a system generated unique application Key and customer ID gets shared with the developer
•	All verification servers are hosted on AWS cloud and are horizontally scalable. The solution is scalable in terms of TPS capacity as per customer requirements.



Scope of Service: (U2opia responsibility) 
•	U2opia would be responsible for SMS & Voice connectivity as per the agreed SLA terms 
•	U2opia would be responsible for HW & SW (deployment & maintenance)
•	All product features and upgrades made by U2opia would be made available at no additional cost.



Summary
•	Verify is a single, global, end-to-end, mobile number authentication solution that includes SMS & Missed call 
•	Flexibility and freedom to select any call flow to meet different requirements
•	Customer experience – highest conversion rate with automatic routing
•	Real-time online dashboard to measure success rate
