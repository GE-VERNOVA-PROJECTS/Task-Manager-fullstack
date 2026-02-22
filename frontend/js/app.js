const API = "http://localhost:8081/api/tasks";

const taskTableBody = document.getElementById("taskTableBody");
const modal = document.getElementById("taskModal");
const addTaskBtn = document.getElementById("addTaskBtn");
const cancelBtn = document.getElementById("cancelBtn");
const saveTaskBtn = document.getElementById("saveTaskBtn");

document.addEventListener("DOMContentLoaded", fetchTasks);

addTaskBtn.addEventListener("click", () => {
    document.getElementById("modalTitle").textContent = "Add Task";
    document.getElementById("taskId").value = "";
    modal.classList.remove("hidden");
});

cancelBtn.addEventListener("click", () => {
    modal.classList.add("hidden");
});

saveTaskBtn.addEventListener("click", saveTask);

async function fetchTasks() {
    const res = await fetch(API);
    const tasks = await res.json();
    renderTasks(tasks);
}

function renderTasks(tasks) {
    taskTableBody.innerHTML = "";

    tasks.forEach(task => {
        taskTableBody.innerHTML += `
            <tr>
                <td>${task.title}</td>
                <td>${task.description}</td>
                <td>${task.status}</td>
                <td>${task.priority}</td>
                <td>${task.dueDate}</td>
                <td>
                    <button onclick="editTask(${task.id})">Edit</button>
                    <button onclick="deleteTask(${task.id})" class="danger-btn">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function saveTask() {
    const id = document.getElementById("taskId").value;

    const task = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        status: document.getElementById("status").value,
        priority: document.getElementById("priority").value,
        dueDate: document.getElementById("dueDate").value
    };

    if (id) {
        await fetch(`${API}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(task)
        });
    } else {
        await fetch(API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(task)
        });
    }

    modal.classList.add("hidden");
    fetchTasks();
}

async function deleteTask(id) {
    await fetch(`${API}/${id}`, { method: "DELETE" });
    fetchTasks();
}

async function editTask(id) {
    const res = await fetch(`${API}/${id}`);
    const task = await res.json();

    document.getElementById("modalTitle").textContent = "Edit Task";
    document.getElementById("taskId").value = task.id;
    document.getElementById("title").value = task.title;
    document.getElementById("description").value = task.description;
    document.getElementById("status").value = task.status;
    document.getElementById("priority").value = task.priority;
    document.getElementById("dueDate").value = task.dueDate;

    modal.classList.remove("hidden");
}