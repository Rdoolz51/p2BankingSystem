// 'use client'
import Link from "next/link"
import Image from "next/image";

import styles from './News.module.css'
import { content } from "./content";

async function getData() {
  const pageSize = 5
  try {
    const res = await fetch(`https://newsapi.org/v2/everything?domains=wsj.com&apiKey=${process.env.NEWS_API}&pageSize=${pageSize}`);
    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      return null;
    }
  } catch (e) {
    console.error('Something went wrong fetching news...', e)
    return null;
  }
}


const News = async () => {
  const data = await getData();
  let news = content; //backup in case our API fails to fetch
  if(data) {
    news = data.articles;
  }

  console.log(news);
  return (
    <div>
      news
    </div>
  )
}

export default News;