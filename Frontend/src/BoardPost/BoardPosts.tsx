import React, { useEffect, useState } from "react";
import { GetBoardPost } from "../Protocol/BoardPost/GetBoardPost";
import { PutBoardPost } from "../Protocol/BoardPost/PutBoardPost";


export function BoardPosts() : React.ReactElement{

  const [getPostCount, setGetPostCount] = useState<number>(10);
  const [startId, setStartId] = useState<number>(Number.MAX_SAFE_INTEGER);
  const [getPostResults, setGetPostResults] = useState<GetBoardPost[]>([]);



  const [currentPostTitle, setCurrentPostTitle] = useState<string>("");
  const [currentPostPassword, setCurrentPostPassword] = useState<string>("");
  const [currentPostContent, setCurrentPostContent] = useState<string>("");


  async function GetPosts(startId : number, count : number){
    let requestParam = new URLSearchParams();
    
    requestParam.append("startId", startId.toString());
    requestParam.append("count", count.toString());

    let response = await fetch(
      `/boardpost?${requestParam.toString()}`,
      {
        method:"GET"
      }
    );

    let responseJson = await response.json();

    let resultPosts = responseJson as GetBoardPost[];

    setGetPostResults(resultPosts);
  }

  useEffect(() => {
    GetPosts(startId, getPostCount);
  }, [])

  return (
    <div>
      <h1>BOARD</h1>
      <div>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
            </tr>
          </thead>
          <tbody>
            {
              getPostResults.length > 0 
                ? getPostResults.map((post) => {
                  return (
                    <tr>
                      <td>{post.id}</td>
                      <td>{post.title}</td>
                    </tr>
                  )
                })
                : <div>NO Results found.</div>
            }
          </tbody>
        </table>
      </div>
      <div>
        <h2>Test Post Writer</h2>
        <div>
          <span>Title</span>
          <input type="text" onChange={(ev) => {
            setCurrentPostTitle(ev.target.value);
          }} value={currentPostTitle}/>
          <br/>
          <span>Password</span>
          <input type="password" onChange={(ev) => {
            setCurrentPostPassword(ev.target.value);
          }} value={currentPostPassword}/>
          <br/>
          <span>Content</span>
          <textarea onChange={(ev) => {
            setCurrentPostContent(ev.target.value);
          }} value={currentPostContent}></textarea>
          <button onClick={(ev) => {
            async function PutBoardPost(){
              let response = await fetch(
                `/boardpost`,
                {
                  method:"PUT",
                  headers: {
                    "Content-Type": "application/json",
                  },
                  body: JSON.stringify({
                    title : currentPostTitle,
                    password : currentPostPassword,
                    content : currentPostContent
                  })
                }
              );

              if(response.ok){
                alert("File upload complete.");
                setCurrentPostTitle("");
                setCurrentPostPassword("");
                setCurrentPostContent("");

                await GetPosts(Number.MAX_SAFE_INTEGER, getPostCount);
              }
            }

            PutBoardPost();
          }}>POST</button>
        </div>
      </div>
    </div>
  )
}