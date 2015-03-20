angular.module('starter.services', [])

.factory('Chats', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: '@pedrofsn',
    lastText: 'I\'m Android Developer',
    face: 'https://raw.githubusercontent.com/pedrofsn/pedrofsn/gh-pages/images/me.jpg'
  }, {
    id: 1,
    name: '@luatane',
    lastText: 'Eu sei ELiS',
    face: 'https://pbs.twimg.com/profile_images/527423002674487296/FJ6MF0lq.jpeg'
  }];

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    }
  };
});
