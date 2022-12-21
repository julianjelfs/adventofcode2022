(ns advent-of-code.day-07
  (:require [clojure.string :as str])
)

(defn process_command [path tree [line & lines]]
  (if-not line
    tree
    (let [[_ ls dir bytes file cd] (re-find #"[$] (ls)|dir (.+)|(\d+) (.+)|[$] cd (.+)" line)]
      (cond 
        ls (process_command path tree lines)
        dir (process_command path (assoc-in tree (conj path dir) {}) lines)
        file (process_command path (assoc-in tree (conj path file) (read-string bytes)) lines)
        cd (case cd 
          "/" (process_command [] tree lines)
          ".." (process_command (pop path) tree lines)
          (process_command (conj path cd) tree lines))))))

(defn parse_tree [input]
  (process_command [] {} input))

; ok - we are getting the right size for the root directory 
; we just need to aggregate the size for each path as we go along now
(defn dir_size [tree]
  (let [node_size (fn n [[k v]]
    (if (int? v)
      v
      (dir_size v)
    ))]
    (reduce + 0 (map node_size tree))))

(defn part-1
  "Day 07 Part 1"
  [input]
   (dir_size (parse_tree (str/split-lines input))))

(defn part-2
  "Day 07 Part 2"
  [input]
  input)
