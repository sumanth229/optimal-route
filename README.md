# APIs 
1. create grid: creates grid/route\
    input format : list of nodes as json to be passed in the request body
  ```JSON
  {
  "edges": [
                   {
                        "u" : "a",
                        "v" : "b",
                        "distance" : 10,
                        "speedRate" : 3
                    },{
                        "u" : "a",
                        "v" : "c",
                        "distance" : 5,
                        "speedRate" : 1
                    },{
                        "u" : "b",
                        "v" : "d",
                        "distance" :3,
                        "speedRate" : 2
                    },{
                        "u" : "c",
                        "v" : "f",
                        "distance" : 4,
                        "speedRate" : 1
                    },{
                        "u" : "d",
                        "v" : "e",
                        "distance" : 5,
                        "speedRate" : 4
                    },{
                        "u" : "d",
                        "v" : "f",
                        "distance" :7,
                        "speedRate" : 1
                    },{
                        "u" : "e",
                        "v" : "f",
                        "distance" : 6,
                        "speedRate" : 2
                    }
  ]
}
```
2. update grid: updates grid/route\
    a. operations supported\
                        1. add edge : add edge to the existing grid\
                        2. modify edge : update speedRate and distance of existing edge of the grid\
                        3. delete edge : delete an edge of the existing grid\
    b. input format :  list of nodes as json to be passed in the request body
 ```JSON
 {
      "edges":[
                    {
                        "u" : "a",
                        "v" : "b",
                        "distance" : 10,
                        "speedRate" : 3,
                        "operationType" : "ADD"
                    },
                    {
                        "u" : "a",
                        "v" : "b",
                        "operationType" : "DELETE"
                    },
                    {
                        "u" : "a",
                        "v" : "b",
                        "distance" : 7,
                        "speedRate" : 4,
                        "operationType" : "UPDATE"
                    }
    ]
}
```

3. get route : fetch the grid/route created
4. get optimal path : get the optimal path between source and destination
    a. input format :
                    request param : source, destination

url : http://localhost:8080/swagger-ui.html#!/optimal-route-controller/

Note : To support update of existing grid, Map is being used instead of array or list to store graph.               
