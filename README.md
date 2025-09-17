# Gusto Lunch Menu

Welcome to the Gusto Technical Phone Assessment! This project is a starting point for the below problem.

## The Project

This project will serve as a starting point in the interview process. Once this project has been evaluated by our engineering team, you may be invited for a full panel interview with various members of the team. At the full panel interview, we'll review this project with you and ask you to enhance it with a new set of requirements. 

As you tackle this problem, keep in mind we'll be looking for the following:

* Code structure and software architecture principles
* Ability to navigate ambiguous requirements
* Your unique strengths as a software engineer

At the end of this session, make sure you push what you've completed and your interview has access to your repo.

## The Problem

At Gusto, we really enjoy our catered lunches. We use a rotating schedule for the lunch menu, but it has been tricky for us to keep track of it. To help us plan our week, we'd like an app that displays the lunch schedule on a calendar. You'll find the raw lunch schedule data below.

### Rotating Lunch Schedule

* Week 1
  * Monday - Chicken and waffles
  * Tuesday - Tacos
  * Wednesday - Curry
  * Thursday - Pizza
  * Friday - Sushi
* Week 2
  * Monday - Breakfast for lunch
  * Tuesday - Hamburgers
  * Wednesday - Spaghetti
  * Thursday - Salmon
  * Friday - Sandwiches

## Starting Point

This project has a few things set up for you to start (detailed below), but don't feel glued to them. If there's a library we didn't include, feel free to add it as a dependency. And if there's a tool we added, it's not required that you use it!

* Dependencies in [build.gradle](app/build.gradle.kts)
  * In addition to dependencies added from making a new project, we've added a few common AndroidX/Jetpack libraries, as well as Kotlin Coroutines.
  * While you're welcome to use any async tools you like, the data source provided uses `suspend`. If you'd like to not use coroutines, you'll need to tweak the data source to align with your tools
* [MainActivity](app/src/main/java/com/gusto/lunchmenu/MainActivity.kt)
  * A simple `Activity` used as the entry point for the app
    * See `onCreate` for two options to get started. Uncomment one and remove the other.
      * `setupComposeUI` - Simple layout with `Scaffold` displaying `MainActivityPrompt`
      * `setupFragmentUI` - Simple layout with a `Toolbar` and a `FragmentContainerView` displaying the `MainFragment`
* [MainFragment](app/src/main/java/com/gusto/lunchmenu/MainFragment.kt)
  * A blank `Fragment` with a `ViewBinding` setup for easy access to views 
* [LunchMenuDataSource](app/src/main/java/com/gusto/lunchmenu/LunchMenuDataSource.kt)
  * This is where you'll access the above menu. We've provided a very basic faked async resource that returns it's data after a 3 second delay to simulate a network request.


