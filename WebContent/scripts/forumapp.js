

var forumapp = angular.module('forumApp', []);

forumapp.controller('ForumController', ['$scope', '$http', function($scope, $http){





    //------------------------------ CUSTOMER DETAILS -------------------------------//
    $scope.forumSignIn = {username:"", password:""};

    $scope.signInUser = function(){
        console.log("Sign In controller called");
        console.log($scope.forumSignIn.password);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumSignInServlet",
            data: $scope.forumSignIn
        }).success(function(data){
            $scope.status=data;
            console.log(data);
        });
    };


    //------------------------------ ADMIN DETAILS -------------------------------//
    $scope.forumadmin = {adminID:"", adminName:""};

    $scope.setAdminDetails = function(){
        console.log("Admin details controller called");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumAdminServlet",
            data: $scope.forumadmin
        }).success(function(data){
            $scope.status=data;
            console.log(data);
        });
    };

    //------------------------------ MODERATOR DETAILS -------------------------------//
    $scope.forummoderator = {moderatorID:"", moderatorName:"", forumAdminID:""};

    $scope.setModeratorDetails = function(){
        console.log("Moderator details controller called");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumModeratorServlet",
            data: $scope.forummoderator
        }).success(function(data){
            $scope.status=data;
            console.log(data);
        });
    };


    //------------------------------ CATEGORY DETAILS -------------------------------//

    //------------- CATEGORY DETAILS - Populate categories on user page --------------//

    $scope.categorydetails = {category:""};

    $scope.setCategoryDetailsInitially = function(){
        console.log("setCategoryDetailsInitially called");
        console.log($scope.categorydetails.category);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumCategoryServlet1",
            data: $scope.categorydetails
        }).success(function(data){
            $scope.categorystatus=data;
            console.log(data);
        });
    };


    //------------- CATEGORY DETAILS - Populate categories table on admin page ------------//

    $scope.categorydetails = {category:""};

    $scope.setCategoryDetailsInitiallyAdminPage = function(){
        console.log("Category details controller called");
        console.log($scope.categorydetails.category);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumCategoryPopulateInAdminServlet1",
            data: $scope.categorydetails
        }).success(function(data){
            $scope.categoryListAdminPage=data;
            console.log(data);
        });
    };

    //------------- CATEGORY DETAILS - Add category through admin page --------------//

    $scope.categorydetails = {category:"", moderatorID:""};

    $scope.setCategoryDetails = function(){
        console.log("Category details controller called");
        console.log($scope.categorydetails.category);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumAddCategoryServlet1",
            data: $scope.categorydetails
        }).success(function(data){
            $scope.categorystatus=data;
            console.log(data);
        });
    };

    //------------------------------ TOPIC DETAILS ----------------------------//
    $scope.forumtopic = {topicID:"", topic:"", categoryID:"", dateOfCreation:"", customerID:"", approved:""};


    //------------------------- Populate topics on Moderator page --------------------//

    $scope.topicsListModeratorPage = {topicID:""};

    $scope.setTopicsInitiallyModeratorPage = function(id){
        $scope.topID = {topicID:id};
        console.log("Topics list controller called");
        console.log($scope.categorydetails.category);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicPopulateInModeratorServlet1",
            data: $scope.topID
        }).success(function(data){
            $scope.topicsListModeratorPage=data;
            console.log(data);
        });
    };


    //------------------- Add Topic -----------------------//
    $scope.forumAddTopic = {topic:"", topicDesc:""};

    $scope.addTopicIntoDatabase = function(){
        console.log("Add topic controller called");
        console.log($scope.categorydetails.category);
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumAddTopicServlet1",
            data: $scope.forumAddTopic
        }).success(function(data){
            $scope.categoryListAdminPage=data;
            console.log(data);
        });
    };


    //------------- Populate topics on user page on click of a category ------------//

    $scope.setTopicsUserPage = function(id){
        $scope.categID = {categoryID:id};
        console.log($scope.categID.categoryID);
        console.log("setTopicsUserPage() called...");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicUserServlet1",
            data: $scope.categID
        }).success(function(data){
            $scope.topicsListUserPage=data;
            console.log(data);
        });
    };


    //-------------- Populate the topic name and description on click of a topic -------------//
    $scope.forumtopicID = {topicID:""};
    $scope.setTopicDetailsUserPage = function(id){
        $scope.topicID = {topID:id};
        console.log("setTopicDetailsUserPage() called...");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicDetailsUserServlet1",
            data: $scope.topicID
        }).success(function(data){
            $scope.topicsDescriptionUserPage=data;
            console.log(data);
            console.log(data)
        });
    };


    //------------------ Populate the topic's comments on click of a topic -----------------//
    $scope.forumtopicID = {topicID:""};
    $scope.setTopicCommentsUserPage = function(id){
        $scope.topicID = {topID:id};
        console.log("setTopicDetailsUserPage() called...");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicCommentsUserServlet1",
            data: $scope.topicID
        }).success(function(data){
            $scope.topicsCommentsUserPage=data;
            console.log(data);
        });
    };


    //------------------ Approve Topics -----------------//

    $scope.topicID = {topID:""};

    $scope.approveTopic = function(){
        console.log($scope.topicID.topID);
        console.log("approveTopic() called...");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicApproveServlet",
            data: $scope.topicID
        }).success(function(data){
            $scope.topicsReturned=data;
            console.log(data);
        });
    };

    //------------------ Remove Topics -----------------//

    $scope.topicIDtoRemove = {topID:""};

    $scope.removeTopic = function(){
        console.log($scope.topicID.topID);
        console.log("removeTopic() called...");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumTopicRemoveServlet",
            data: $scope.topicIDtoRemove
        }).success(function(data){
            $scope.topicsReturned=data;
            console.log(data);
        });
    };


    //------------------------------ COMMENT DETAILS ----------------------------//

    $scope.forumcomment = {comment:""};
    //---------------------------- Add Comment ------------------------------//
    $scope.addCommentDetails = function(){
        console.log("Comment details controller called");
        $http({
            method: 'POST',
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            url: "ForumAddCommentServlet",
            data: $scope.forumcomment
        }).success(function(data){
            $scope.status=data;
            console.log(data);
        });
    };


    //--------------------- CAPTCHA ------------------------//

    $scope.redirect = function(){
        window.location = "#/captcha.jsp"
    }
}])