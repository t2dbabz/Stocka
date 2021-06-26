# stocka-mobile-pjt-114
Stocka is an inventory digital solution that will take account of trader's sales and stocks, calculates their profits or losses over a specific period of time and offer 
suggestions to build profits.

# More Information About the Project
- Figma Design <a href="https://www.figma.com/file/ttEUL6WHpolmOJpNVVONgY/Stocka-APP?node-id=0%3A1">here</a>

# How To contribute to the project.

## How to Fork and clone the project
- Fork the original repo <a href="https://github.com/zuri-training/stocka-mobile-pjt-114.git">here</a>
- Clone the repo to your local system. Run this command in your command line(Command prompt or Microsoft Powershell or Git Bash)     
  <pre>
    git clone <b>LINK</b>
  </pre>
  where **LINK** is the link to your FORK Of the repo e.g https://github.com/**YourUsername**/stocka-mobile-pjt-114.git      
  To get the link,
    - Go to your fork of the repo
    - Click on the Big green **Clone** button.
    - Click on Https
    - Copy the link. That is thre required link.
- After cloning the fork to your computer navigate into the project folder by running the following command      
    <pre>
    cd  
  </pre>     

## Syncing Your Project to the original Repository
As you are working on your local branch and making changes to your fork, it's possible that new changes could be added to the original repository. In that case you have to pull/fetch changes from the original repo and not from your fork.
- run ```git remote -v``` in your command line. You'll see it lists your repo link and shows that it fetches and pushes to your repo.
- To be able to pull from the original repo
  - Go to the original repo  <a href="https://github.com/zuri-training/stocka-mobile-pjt-114.git">here</a>
  - Click on clone and copy the link
- In your command line run 
<pre>
   git remote add upstream <b>LINK</b>
</pre>    
where LINK is the link copied from the original repo.
- Run ```git rmeote -v``` again and you'll see that you can now fetch from the original repo.
- To pull changes from the original repo run ```git pull``` and all changes made will be applied to your local repo.

## Making Pull Requests and Contributing.
- When you have worked on a task assigned to you, that code will need to be aded to the main repo.
- We do this using pull requests.
- After you have made all commits necessary to your fork it is time to make a pull request
- Click on Pull Requests
- Create a new Pull Request
- Give the pull request a name that signifies the task you were given (e.g if you were to create the login screen you can name the Pull Request **Login Screen**)
- Give adequate explanation of the things you did in your commits in the pull request
- Usually the pull request should be able to automatically merge. If it does not autmatically merge, Then stop this process and follow the steps below, else continue downwards.
  - If it does not automatically merge,
  - Go back to your code
  - Run ```git pull``` in your command line
  - Solve any merge conflicts.
  - run ```git push``` to push your changes to your fork. 
  - Create the pull request again.
- Create pull request and notify @seunbayo so he can review and merge your pull request
- Also notify me so @Ose4g so hw knows you have completed your task successfully.


#### If there are any questions ask @t2dbabz on Slack.

#### Useful links
- https://docs.github.com/en/get-started/quickstart/fork-a-repo
- https://www.youtube.com/watch?v=f5grYMXbAV0
- https://www.youtube.com/watch?v=HbSjyU2vf6Y
