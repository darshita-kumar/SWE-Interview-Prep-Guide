## Googleyness Interview prep

Check below collection:
https://leetcode.com/discuss/post/5963463/googlyness-frequently-asked-questions-by-55sh/

> **General Tips:** Use:
>  - STAR method (Situation, Task, Action, Result) for behavioural questions
>  - CFAS method (Clarity, Framework, Assumption, Solution) for hypothetical questions

-----------

> **Framework for Handling Negative Culture (CFAS):**
> - **Clarify:**
>     - Is the negative culture limited to my team or across the organization?
>     - Has it always been this way or was it triggered by an event?
>     - Where is the negativity coming from — leadership, strategy, or policy?
>     - Is this a personal opinion or do others share it?
>     - Has any past attempt been made to fix it?
> - **Framework:**
>     - Goals, historical data, involve stakeholders, inclusivity, training, communication.
> - **Assumptions:**
> - **Solution:**
>     - Talk to the team, understand pain points, derive action plans, research external solutions, conduct workshops, create repeatable models.
> - **Success Measurements:**
>     - Surveys.

-----------

> **How did opposing ideas lead to something positive? (STAR):**
> - https://www.youtube.com/watch?v=ceBVswJldnI&ab_channel=JeffHSipe-PracticeInterviews
> - **Situation & Task:** (30 secs)
>     - State role and company, speak 1 to 2 sentences about the specific situation
>     - State timeline of situation (for urgency, complexity, difficulty)
> - **Action:** (About 3 mins)
>     - How did YOU contribute in this situation (3-4 WHAT did you do, support each with HOW did you do it)
>     - Examples with data, communication with critical stakeholders
>     - Then test and execute, gather data
>     - Launching, presenting, documenting, incorporating feedback
> - **Results:** (30 secs)
>     - 1st: Answers the question
>     - 2nd: Bring numbers/stats into the equation
>     - 3rd: Repeatability - how did you use the learnings in other situations


-----------

### My events to speak about:

1. Dedup logic implementation
   - Task/Situation:
       - Tasked with the creation of a system which defines the rules for the priority of certain data over another
       - It involved creation of a decision tree for the Mexico market data
       - A business ask, given that there was no uniformity in the prority of data between various sources
   - Action:
       - I started by examining the structure of the existing system in which this logic was required to be emdedded
       - Thereafter, I held discussions with the critical stakeholders as the impact would be huge
       - The system was required to be robust as the priority rules could change at any point of time owing to a change in business requirements
       - I examined the legacy systems in order to understand how this task was being performed by them. The only problem with them was: they were rigid systems with no flexibiity
       - I took suggestions from my seniors about the approach to be taken and created an archtecture for the sub-system
       - Held architectural review discussions with the entire team and product managers, incorporated their suggestions in my design
       - Once I had everything ready, I set a timeline for the delivery of the system and worked to fulfill it
       - Simulatenously we also came out with a phased rollout plan to minimise impact to the customers,
       - Based on the success of the rollout and feedback received during it, we rolled out the complete feature to production
    - Results:
       - The system gave the business better control over what data to show on the website
       - The tool led to uniformity in the data priority rules, governing over 85+ million records for the MX market
       - Due to it's robustness, it was leveraged as a tool for the Chile market as well as Bodega market, defining the rules for over a million records
       - Best part? The business could ask for a flip in the priority of one data source to another any time
         
2.Variants functionality
   - Task/Situation:
       - To be able to group data in the Mexico market leading to a better customer experience, similar to certain competitors
       - It was a business ask which was predicted to boost sales by a whopping 40% 
   - Action:
        - The US market had already implemented this functionality
        - My task was to build a similar data pipeline, so I studied the implementation aspects in the US market
        - Thereafter, I held discussions with the critical stakeholders and product managers to understand and document the requirement so that I don't leave any gaps
        - I took suggestions from my seniors about the approach to be taken and created an archtecture for the sub-system
        - Held architectural review discussions with the entire team and product managers, incorporated their suggestions in my design
        - Once I had everything ready, I set a timeline for the delivery of the system and worked to fulfill it
        - I worked along the timelines and saw it to completion
    - Results:
        - Reduced duplication of 1.5 million records and boosted sales by 42% as predicted
        - It improved customer browsing experience
        - Due to it's robustness, the functionality was leveraged for the Chile market as well
        
3. Badging tool
   - Task/Situation:
        - Introduction of labels functionality in Mexico market
        - Ideally a tedious task for business as it involved the manual creation of labels in the legacy systems
    - Action:
         - Understanding pain points: The legacy systems offered a poor User Interface with extremely slow processing speeds and TTL for the labels
         - I needed to build a faster, easy to use system for the sellers
         - I worked closely and collaborated with product to obtain the requirements from the end-users of the system
         - Chalked out the architecture in a few, raw, whiteboarding sessions with my team lead and architect
         - Documented, presented, incorporated reviews, etc.
         - Reasearched and found a tool being used for it in the Canada market
         - Decided to leverage it and built the system according to the timeline provided earlier
         - Provided the training documentation and a short presentation about how the tool has to be used
         - Conducted a pilot for a few labels in production while also demonstrating the use of the tool
    - Results:
         - Successfully made the process of badge creation faster and easy for the business users
         - Great impact in the Hot Sale season
         - Received a Bravo award for it
         - Later tool was leveraged for a new market called 'Sams' which was onboarded into our system

5. Canada BVSHELL consumption job
    - Task/Situation:
      - Consumption of 800 million records via a data pipeline without impacting the downstream systems and choking their pipelines
    - Action:
      - The challenge was learning a new technology and being able to use it efficiently in a production environment with huge amounts of data
    - Results:
      - 

6. Kafka configs fine tuning
   
7. EA launch
    - Task/Situation:
      - 
    - Action:
    - Results:
